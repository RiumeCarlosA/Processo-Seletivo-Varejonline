package br.com.varejonline.riume.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.varejonline.riume.dto.response.MovimentacaoResponseDTO;
import br.com.varejonline.riume.model.Movimentacao;
import br.com.varejonline.riume.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoRepository repository;
	
	public MovimentacaoResponseDTO convertMovimentacaoToMovimentacaoResponseDTO(Movimentacao movimentacao) {
		return MovimentacaoResponseDTO.builder()
						.id(movimentacao.getId())
						.qtd(movimentacao.getQtd())
						.motivo(movimentacao.getMotivo())
						.movimentos(movimentacao.getMovimento())
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

}
