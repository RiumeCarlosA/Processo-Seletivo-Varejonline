package br.com.varejonline.riume.model.usuarios;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.varejonline.riume.model.enums.Perfil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@DynamicUpdate
@Entity
public abstract class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@NotNull
	@NotEmpty
	@Column(name = "nome", length = 255)
	protected String nome;
	
	@NotNull
	@NotEmpty
	@Column(name = "usuario", nullable = false)
	protected String usuario;
	
	@NotNull
	@NotEmpty
	@Column(name = "senha", nullable = false)
	protected String senha;
	
	@ToString.Exclude
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	protected Set<Integer> perfis = new HashSet<>();
	
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	protected Instant dataCriacao = Instant.now();
	
	@Column(name = "deleted", columnDefinition = "boolean default false")
	protected boolean deleted = false;

	
	public Pessoa() {
		addPerfil(Perfil.OPERADOR);
	}
	
	public Pessoa(String nome, String usuario, String senha) {
		this.usuario = usuario;
		this.nome = nome;
		this.senha = senha;
		addPerfil(Perfil.OPERADOR);
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public Set<Integer> getPerfil() {
		return this.perfis;
	}
	
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}
	
	
}
