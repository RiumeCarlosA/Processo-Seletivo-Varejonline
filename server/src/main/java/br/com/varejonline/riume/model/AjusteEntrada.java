package br.com.varejonline.riume.model;

import java.time.Instant;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@Entity
@DiscriminatorValue("AJUSTE_ENTRADA")
public class AjusteEntrada extends Ajuste {

	private static final long serialVersionUID = 1L;
	
	@Builder
	public AjusteEntrada(Instant dataMovimentacao, Integer qtd, String motivo, Produto produto) {
		this.dataMovimentacao = dataMovimentacao;
		this.qtd = qtd;
		this.motivo = motivo;
		this.produto = produto;
	}
	
}
