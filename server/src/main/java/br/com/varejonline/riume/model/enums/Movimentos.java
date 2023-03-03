package br.com.varejonline.riume.model.enums;

import lombok.Getter;

@Getter
public enum Movimentos {

	ENTRADA(0, "ENTRADA"), SAIDA(1, "SAIDA"), SALDO_INICIAL(2, "SALDO_INICIAL"), AJUSTE_ENTRADA(3, "AJUSTE_ENTRADA"), AJUSTE_SAIDA(4, "AJUSTE_SAIDA");
	
	private Integer codigo;
	
	private String descricao;
	
	private Movimentos(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static Movimentos toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Movimentos x : Movimentos.values()) {
			if(cod.equals(x.getCodigo())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("Perfil inválido");
	}
}
