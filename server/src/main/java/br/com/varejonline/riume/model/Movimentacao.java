package br.com.varejonline.riume.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.varejonline.riume.model.enums.Movimentos;
import br.com.varejonline.riume.model.usuarios.Pessoa;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
public class Movimentacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "motivo")
	private String motivo;
	
	@ManyToOne
	private Pessoa pessoa;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Produto produto;
	
	@ToString.Exclude
	private Movimentos movimentos;
	
	@Column(name = "data_movimentacao")	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	private Instant dataMovimentacao = Instant.now();
	
	@Builder
	public Movimentacao(Integer quantidade, String motivo, Produto produto, Pessoa pessoa) {
		this.quantidade = quantidade;
		this.motivo = motivo;
		this.produto = produto;
		this.pessoa = pessoa;
	}
	
	@Builder
	public Movimentacao(Produto produto, Pessoa pessoa) {
		this.quantidade = produto.getSaldoInicial();
		this.produto = produto;
		this.pessoa = pessoa;
		this.movimentos = Movimentos.SALDO_INICIAL;
	}
	
	
	
}
