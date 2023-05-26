package com.trabalhoFinal.apiEcommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.dto.CategoriaProdDTO;
import com.trabalhoFinal.apiEcommerce.dto.ProdutoDTO;
import com.trabalhoFinal.apiEcommerce.entities.Produto;
import com.trabalhoFinal.apiEcommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}

	public List<ProdutoDTO> getAllProdutosDTO() {
		ModelMapper modelMapper = new ModelMapper();

		List<ProdutoDTO> produtosDTO = new ArrayList<>();

		for (Produto produto : produtoRepository.findAll()) {
			ProdutoDTO novoProdutoDto = modelMapper.map(produto, ProdutoDTO.class);
			novoProdutoDto.setCategoriaProdDto(modelMapper.map(produto.getCategoria(), CategoriaProdDTO.class));
			produtosDTO.add(novoProdutoDto);
		}
		return produtosDTO;
	}

	public Produto getProdutoById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}

	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto updateProduto(Produto produto, Integer id) {
		return produtoRepository.save(produto);
	}

	public Boolean delProduto(Integer id) {
		Produto produto = produtoRepository.findById(id).orElse(null);

		if (produto != null) {
			produtoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}