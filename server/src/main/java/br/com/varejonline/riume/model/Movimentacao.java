package br.com.varejonline.riume.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Movimentacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(name = "data_movimentacao")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	protected Instant dataMovimentacao;
	
	@Column(name = "quantidade")
	protected Integer qtd;
	
	@Column(name = "motivo")
	protected String motivo;
	
	@ManyToOne
	protected Produto produto;
	
	@ToString.Exclude
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "MOVIMENTO")
	protected Set<Integer> movimento = new HashSet<>();
	
	
	
	
}
