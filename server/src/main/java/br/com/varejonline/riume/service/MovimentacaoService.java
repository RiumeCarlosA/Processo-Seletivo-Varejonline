package br.com.varejonline.riume.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.varejonline.riume.dto.request.MovimentacaoCreateDTO;
import br.com.varejonline.riume.dto.response.MovimentacaoResponseDTO;
import br.com.varejonline.riume.exception.NotFoundException;
import br.com.varejonline.riume.model.Movimentacao;
import br.com.varejonline.riume.model.Produto;
import br.com.varejonline.riume.model.enums.Movimentos;
import br.com.varejonline.riume.model.usuarios.Pessoa;
import br.com.varejonline.riume.repository.MovimentacaoRepository;
import br.com.varejonline.riume.repository.ProdutoRepository;
import br.com.varejonline.riume.repository.usuarios.PessoaRepository;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired
	private PessoaRepository repositoryP;
	
	@Autowired
	private ProdutoRepository repositoryProduto;
	
	public MovimentacaoResponseDTO convertMovimentacaoToMovimentacaoResponseDTO(Movimentacao movimentacao) {
		return MovimentacaoResponseDTO.builder()
						.id(movimentacao.getId())
						.qtd(movimentacao.getQuantidade())
						.motivo(movimentacao.getMotivo())
						.movimentos(movimentacao.getMovimentos())
						.nomePessoa(movimentacao.getPessoa().getNome())
						.nomeProduto(movimentacao.getProduto().getNome())
						.dataMovimentacao(movimentacao.getDataMovimentacao())
						.build();
	}
	
	@Transactional(readOnly = true)
	public List<MovimentacaoResponseDTO> findAll() {
		List<Movimentacao> list = repository.findAll();
		return list.stream().map(obj -> convertMovimentacaoToMovimentacaoResponseDTO(obj))
				  			.collect(Collectors.toList());
	}

	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public MovimentacaoResponseDTO create(MovimentacaoCreateDTO objDTO) {
		Optional<Pessoa> usuario = repositoryP.findByUsuario(objDTO.getNomePessoa());
		Optional<Produto> produto = repositoryProduto.findByNome(objDTO.getNomeProduto());
		
		if(objDTO.getMovimentos() == 0) {produto.get().setSaldo(objDTO.getQuantidade() + produto.get().getSaldo());}
		
		if(objDTO.getMovimentos() == 1) {
			if(objDTO.getQuantidade() - produto.get().getSaldo() > produto.get().getQtdMin()) {
				produto.get().setSaldo(objDTO.getQuantidade() - produto.get().getSaldo());
			}else { new NotFoundException("{error.movimentacao.create.movimentacao.not-found}"); }
		}
		
		if(objDTO.getMovimentos() == 2) {new NotFoundException("{error.movimentacao.create.movimentacao.not-found}");}
		
		if(objDTO.getMovimentos() == 3) {produto.get().setSaldo(objDTO.getQuantidade() + produto.get().getSaldo());}
		
		if(objDTO.getMovimentos() == 4) {
			if(objDTO.getQuantidade() - produto.get().getSaldo() > produto.get().getQtdMin()) {
				produto.get().setSaldo(objDTO.getQuantidade() - produto.get().getSaldo());
			}else { new NotFoundException("{error.movimentacao.create.movimentacao.not-found}"); }
		}
		repositoryProduto.save(produto.get());
		
		if(usuario.isPresent() && produto.isPresent()) {
			Movimentacao obj = new Movimentacao(objDTO.getQuantidade(), objDTO.getMotivo(), produto.get(), usuario.get());
			obj.setMovimentos(Movimentos.toEnum(objDTO.getMovimentos()));
			repository.save(obj);
			
			return convertMovimentacaoToMovimentacaoResponseDTO(obj);
		}
		
		new NotFoundException("{error.movimentacao.create.movimentacao.not-found}");
		
		return null;
	}
}
