package com.carnauba.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.carnauba.entity.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"id","nome","preco","estoque"})
public class ProdutoVO extends RepresentationModel<ProdutoVO> implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @JsonProperty("id")
	private Long id;
    @JsonProperty("nome")
	private String nome;
    @JsonProperty("estoque")
	private Integer estoque;
    @JsonProperty("preco")
	private Double preco;
    
    //trasforma um Produto em ProdutoVO
    public static ProdutoVO create(Produto produto) {
    	return new ModelMapper().map(produto, ProdutoVO.class);
    }

}
