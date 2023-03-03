package br.com.varejonline.riume.dto.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.varejonline.riume.model.Produto;
import br.com.varejonline.riume.model.enums.Movimentos;
import br.com.varejonline.riume.model.usuarios.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimentacaoResponseDTO {
	
	private Integer id;
	
	private Integer qtd;
	
	private String motivo;
	
	private String nomePessoa;
	
	private String nomeProduto;
	
	private Movimentos movimentos;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	private Instant dataMovimentacao;
	
	
	public MovimentacaoResponseDTO(Integer id, Integer qtd, String motivo, Pessoa pessoa, Produto produto, Movimentos movimento, Instant dataMovimentacao) {
		this.id = id;
		this.qtd = qtd;
		this.motivo = motivo;
		this.nomePessoa = pessoa.getNome();
		this.nomeProduto = produto.getNome();
		this.dataMovimentacao = dataMovimentacao;
		this.movimentos = movimento;
	}
}
