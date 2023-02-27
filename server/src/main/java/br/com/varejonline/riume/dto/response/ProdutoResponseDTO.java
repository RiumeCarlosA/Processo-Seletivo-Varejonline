package br.com.varejonline.riume.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProdutoResponseDTO {

	private Integer id;
	
	private String codBarra;
	
	private String nome;
	
	private Integer qtdMin;
		
	private Integer saldoInicial;
	
	private Integer saldo;
	
	@Builder.Default
	private boolean deleted = false;
	
	@Builder
	public ProdutoResponseDTO(String nome, String codBarra, Integer qtdMin, Integer saldoInicial, Integer saldo) {
		this.nome = nome;
		this.codBarra = codBarra;
		this.qtdMin = qtdMin;
		this.saldoInicial = saldoInicial;
		this.saldo = saldo;
	}
	
}
