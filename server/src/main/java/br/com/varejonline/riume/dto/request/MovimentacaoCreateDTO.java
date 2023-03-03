package br.com.varejonline.riume.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimentacaoCreateDTO {
	
	private Integer quantidade;
	
	private String motivo;
	
	private String nomePessoa;
	
	private String nomeProduto;
	
	private Integer movimentos;
	
	public MovimentacaoCreateDTO(Integer quantidade, String motivo, String nomePessoa, String nomeProduto, Integer movimentos) {
		this.quantidade = quantidade;
		this.motivo = motivo;
		this.nomePessoa = nomePessoa;
		this.nomeProduto = nomeProduto;
		this.movimentos = movimentos;
	}
}
