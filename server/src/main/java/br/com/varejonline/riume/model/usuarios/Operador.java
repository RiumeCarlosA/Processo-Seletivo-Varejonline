package br.com.varejonline.riume.model.usuarios;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.varejonline.riume.model.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@Entity
@DiscriminatorValue("OPERADOR")
public class Operador extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@Builder
	public Operador (String nome, String usuario, String senha) {
		super(nome, usuario, senha);
		addPerfil(Perfil.OPERADOR);
	}
	
	@Builder
	public Operador (String nome, String usuario, String senha, Set<Integer> perfis) {
		super(nome, usuario, senha);
		this.perfis = perfis;
	}
}
