package br.com.varejonline.riume.dto.response;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.varejonline.riume.model.enums.Perfil;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class GerenteResponseDTO {

	private Integer id;
	
	private String nome;
	
	private String usuario;
	
	private String senha;
	
	@ToString.Exclude
	private Set<Integer> perfis = new HashSet<>();
	
	@Builder
	public GerenteResponseDTO (Integer id, String nome, String usuario, Set<Integer> perfis) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.perfis = perfis;
	}
	
	@Builder
	public GerenteResponseDTO (String nome, String usuario) {
		this.nome = nome;
		this.usuario = usuario;
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
