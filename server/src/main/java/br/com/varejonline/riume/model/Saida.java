package br.com.varejonline.riume.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Saida implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Produto produto;
	
	private int qtd;
	
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	private Instant dataSaida;
	
	

}
