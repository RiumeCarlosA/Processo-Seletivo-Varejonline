package br.com.varejonline.riume.service.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.varejonline.riume.model.Estoque;
import br.com.varejonline.riume.model.Movimentacao;
import br.com.varejonline.riume.model.Produto;
import br.com.varejonline.riume.model.usuarios.Operador;
import br.com.varejonline.riume.repository.EstoqueRepository;
import br.com.varejonline.riume.repository.MovimentacaoRepository;
import br.com.varejonline.riume.repository.ProdutoRepository;
import br.com.varejonline.riume.repository.usuarios.OperadorRepository;

@Service
public class DBService {
	
	@Autowired
	OperadorRepository operadorR;
	
	@Autowired
	EstoqueRepository estoqueR;
	
	@Autowired 
	MovimentacaoRepository movimentacaoR;
	
	@Autowired
	ProdutoRepository produtoR;
	
	public void instanciaDB() {
		
		Operador operador = new Operador("Riume Carlos", "riume", "123");
		
		Estoque estoque = new Estoque();
		
		Produto coca = new Produto("coca-cola", "1234567891011", 1000);
		
		Produto fanta = new Produto("fanta", "1234537461011", 1000);
		
		List<Produto> listP = new ArrayList<>(Arrays.asList(coca, fanta));
		
		estoque.setProdutos(listP);
	}
}
