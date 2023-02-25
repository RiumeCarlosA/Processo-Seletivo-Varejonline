package br.com.varejonline.riume.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import br.com.varejonline.riume.exception.MovimentacaoInvalid;
import lombok.Data;

@Data
@DynamicUpdate
@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(name = "codigo_barras")
	private String codBarra;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "quantidade_minima", nullable = false)
	private Integer qtdMin;
	
	@ManyToOne
	private Estoque estoque;
	
	@Column(name = "saldo_inicial")
	private Integer saldoInicial;
	
	@Column(name = "deleted", columnDefinition = "boolean default false")
	protected boolean deleted = false;

	public Produto(String nome, String codBarra, Integer qtdMin) {
		this.nome = nome;
		this.codBarra = codBarra;
		this.qtdMin = qtdMin;
	}
	
	public Produto(String nome, String codBarra, Integer qtdMin, Integer saldoInicial, Movimentacao movimentacao) {
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
		this.saldoInicial = saldo;
	}
	
	
}
