package br.com.varejonline.riume.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@DynamicUpdate
@Entity
public class Estoque implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	@JoinColumn(name = "produto_id")
	private List<Produto> produtos = new ArrayList<>();;
	
	@Column(name = "deleted", columnDefinition = "boolean default false")
	protected boolean deleted = false;
}
