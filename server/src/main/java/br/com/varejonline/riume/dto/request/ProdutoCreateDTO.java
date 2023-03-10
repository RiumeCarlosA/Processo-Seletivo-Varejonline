package br.com.varejonline.riume.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoCreateDTO {

	private Integer id;
	
	private String codBarra;
	
	private String nome;
	
	private Integer qtdMin;
		
	private Integer saldoInicial;
	
	private String usuario;
	
	@Builder
	public ProdutoCreateDTO(String nome, String codBarra, Integer qtdMin, Integer saldoInicial, String usuario) {
		this.nome = nome;
		this.codBarra = codBarra;
		this.qtdMin = qtdMin;
		this.saldoInicial = saldoInicial;
		this.usuario = usuario;
	}	
}
