package br.com.varejonline.riume.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

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
	
	@Column(name = "valor_unitario", nullable = false)
	private Double valorUnitario;
	
	@Column(name = "quantidade_minima", nullable = false)
	private Integer qtdMin;
	
	@Column(name = "saldo_inicial", nullable = false)
	static private Integer saldoInicial;
	
	@ManyToOne
	private Estoque estoque;
	
	@Column(name = "deleted", columnDefinition = "boolean default false")
	protected boolean deleted = false;

	public Produto(String nome, String codBarra, Double valorUnitario, Integer qtdMin) {
		this.nome = nome;
		this.codBarra = codBarra;
		this.valorUnitario = valorUnitario;
		this.qtdMin = qtdMin;
	}
	
	
	
}
