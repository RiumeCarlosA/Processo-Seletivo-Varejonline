package br.com.varejonline.riume.model.usuarios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.varejonline.riume.model.usuarios.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@Entity
@DiscriminatorValue("GERENTE")
public class Gerente extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@Builder
	public Gerente (String nome, String usuario, String senha) {
		super(nome, usuario, senha);
		addPerfil(Perfil.GERENTE);
	}
	
}
