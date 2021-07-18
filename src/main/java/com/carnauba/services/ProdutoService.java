package com.carnauba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carnauba.data.vo.ProdutoVO;
import com.carnauba.entity.Produto;
import com.carnauba.exception.ResourceNotFoundException;
import com.carnauba.repository.ProdutoRepository;

import lombok.var;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService (ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	//Create
	public ProdutoVO create(ProdutoVO produtoVO) {
		return ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
	}
	//FindAll
	public Page<ProdutoVO> findAll(Pageable pageable){
		var page = produtoRepository.findAll(pageable);
		return page.map(this::convertToProdutoVO);
	}
	//Convert
	private ProdutoVO convertToProdutoVO(Produto produto) {
		return ProdutoVO.create(produto);
	}
	//FindById
	public ProdutoVO findById(Long id) {
		var entity = produtoRepository.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("not found resource"));
        return ProdutoVO.create(entity);
	}
	
	public ProdutoVO update(ProdutoVO produtoVO) {
		final Optional<Produto> optionaProduto = produtoRepository.findById(produtoVO.getId());
		if(!optionaProduto.isPresent()) {
			new ResourceNotFoundException("not found resource");
		}
		return ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
	}
	
	public void delete(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("not found resource"));
		produtoRepository.delete(entity);
	}
}
