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
public class OperadorResponseDTO {

	private Integer id;
	
	private String nome;
	
	private String usuario;
	
	private String senha;
	
	@ToString.Exclude
	private Set<Integer> perfis = new HashSet<>();
	
	@Builder
	public OperadorResponseDTO (Integer id, String nome, String usuario, String senha, Set<Integer> perfis) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.perfis = perfis;
	}
	
	@Builder
	public OperadorResponseDTO (String nome, String usuario, String senha) {
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
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
