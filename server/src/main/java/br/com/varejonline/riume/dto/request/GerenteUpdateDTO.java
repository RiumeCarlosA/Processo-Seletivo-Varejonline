package br.com.varejonline.riume.dto.request;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.varejonline.riume.model.enums.Perfil;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
public class GerenteUpdateDTO {
	
	private Integer id;
	
	@NotEmpty
	@JsonProperty("nome")
	private String nome;
	
	@NotEmpty
	@JsonProperty("usuario")
	private String usuario;
	
	@NotEmpty
	@JsonProperty("senha")
	private String senha;
	
	@ToString.Exclude
	private Set<Integer> perfis = new HashSet<>();
	
	@Builder
	public GerenteUpdateDTO (Integer id, String nome, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		addPerfil(Perfil.GERENTE);
	}
	
	@Builder
	public GerenteUpdateDTO (Integer id, String nome, String usuario, String senha, Set<Integer> perfis) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.perfis = perfis;
	}
	
	public Set<Perfil> getPerfis() {
		return this.perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public Set<Integer> getPerfil() {
		return this.perfis;
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}
	
}
