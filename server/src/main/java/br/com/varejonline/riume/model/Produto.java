package br.com.varejonline.riume.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.varejonline.riume.exception.MovimentacaoInvalid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "codigo_barras")
	private String codBarra;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "quantidade_minima", nullable = false)
	private Integer qtdMin;
	
	@Column
	private Integer saldo;
	
	@ManyToOne
	private Estoque estoque;
	
	@Column(name = "saldo_inicial")
	private Integer saldoInicial;
	
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	protected Instant dataCriacao = Instant.now();
	
	@Column(name = "deleted", columnDefinition = "boolean default false")
	private boolean deleted = false;

	@Builder
	public Produto(String nome, String codBarra, Integer qtdMin) {
		this.nome = nome;
		this.codBarra = codBarra;
		this.qtdMin = qtdMin;
	}
	
	@Builder
	public Produto(String nome, String codBarra, Integer qtdMin, Integer saldoInicial) {
		this.nome = nome;
		this.codBarra = codBarra;
		this.qtdMin = qtdMin;
		this.setSaldoInicial(saldoInicial);
	}
	
	public void setSaldoInicial(Integer saldo) {
		if(saldo < 0) {
			new MovimentacaoInvalid("error.produto.saldo.saldo-menor-zero");
		}
		if(saldo < this.qtdMin) {
			new MovimentacaoInvalid("error.produto.saldo.saldo-menor-qtd-minima");
		}
		this.saldo = saldo;
		this.saldoInicial = saldo;
	}
	
	
}
