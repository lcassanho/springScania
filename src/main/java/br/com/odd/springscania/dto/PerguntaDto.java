package br.com.odd.springscania.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.odd.springscania.model.Pergunta;

public class PerguntaDto {
	@NotBlank
	@NotNull
	private String descPergunta; 
	@NotBlank
	@NotNull
	private String tipoPergunta;
	@NotNull
	private Long idPesquisa;
	
	public void fromPergunta(Pergunta pergunta) {
		this.descPergunta = pergunta.getDescPergunta();
		this.tipoPergunta = pergunta.getTipoPergunta();
		this.idPesquisa = pergunta.getIdPesquisa();
	}

	public String getDescPergunta() {
		return descPergunta;
	}

	public void setDescPergunta(String descPergunta) {
		this.descPergunta = descPergunta;
	}

	public String getTipoPergunta() {
		return tipoPergunta;
	}

	public void setTipoPergunta(String tipoPergunta) {
		this.tipoPergunta = tipoPergunta;
	}

	public Long getIdPesquisa() {
		return idPesquisa;
	}

	public void setIdPesquisa(Long idPesquisa) {
		this.idPesquisa = idPesquisa;
	}
	
}
