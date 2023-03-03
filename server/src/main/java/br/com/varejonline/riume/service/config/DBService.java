package br.com.varejonline.riume.service.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.varejonline.riume.model.Movimentacao;
import br.com.varejonline.riume.model.Produto;
import br.com.varejonline.riume.model.enums.Movimentos;
import br.com.varejonline.riume.model.usuarios.Gerente;
import br.com.varejonline.riume.model.usuarios.Operador;
import br.com.varejonline.riume.repository.MovimentacaoRepository;
import br.com.varejonline.riume.repository.ProdutoRepository;
import br.com.varejonline.riume.repository.usuarios.GerenteRepository;
import br.com.varejonline.riume.repository.usuarios.OperadorRepository;

@Service
public class DBService {
	
	@Autowired
	private GerenteRepository gerenteR;
	
	@Autowired
	private OperadorRepository operadorR;
	
	@Autowired 
	private MovimentacaoRepository movimentacaoR;
	
	@Autowired
	private ProdutoRepository produtoR;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		
		Gerente gerente = new Gerente("Riume Carlos", "riume", encoder.encode("123"));
		
		Operador operador = new Operador("Carlos Fábio", "carlos", encoder.encode("12345"));
				
		Produto coca = new Produto("coca-cola", "1234567891011", 1000, 1500);
		
		Produto fanta = new Produto("fanta", "1234537461011", 1000, 1500);
		
		Movimentacao mov1 = new Movimentacao(coca, gerente);
		
		Movimentacao mov2 = new Movimentacao(fanta, gerente);
		
		List<Produto> listP = new ArrayList<>(Arrays.asList(coca, fanta));
				
		gerenteR.save(gerente);
		operadorR.save(operador);
		movimentacaoR.saveAll(Arrays.asList(mov1, mov2));
		produtoR.saveAll(listP);
		
		
		
	}
}
