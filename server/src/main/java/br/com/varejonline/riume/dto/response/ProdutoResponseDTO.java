package br.com.varejonline.riume.dto.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoResponseDTO {

	private Integer id;
	
	private String codBarra;
	
	private String nome;
	
	private Integer qtdMin;
		
	private Integer saldoInicial;
	
	private Integer saldo;
	
	@Builder.Default
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	protected Instant dataCriacao = Instant.now();
	
	@Builder.Default
	private boolean deleted = false;
	
	@Builder
	public ProdutoResponseDTO(Integer id, String nome, String codBarra, Integer qtdMin, Integer saldoInicial, Integer saldo, Instant dataCriacao) {
		this.id = id;
		this.nome = nome;
		this.codBarra = codBarra;
		this.qtdMin = qtdMin;
		this.saldoInicial = saldoInicial;
		this.saldo = saldo;
		this.dataCriacao = dataCriacao;
	}
	
}
