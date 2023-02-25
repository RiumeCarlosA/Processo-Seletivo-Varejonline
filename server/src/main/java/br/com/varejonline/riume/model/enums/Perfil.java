package br.com.varejonline.riume.model.enums;

import lombok.Getter;

@Getter
public enum Perfil {
	
	GERENTE(0, "ROLE_GERENTE"), OPERADOR(1, "ROLE_OPERADOR");
	
	private Integer codigo;
	
	private String descricao;
	
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("Perfil inválido");
	}
}
