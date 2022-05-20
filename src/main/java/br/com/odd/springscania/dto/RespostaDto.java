package br.com.odd.springscania.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.odd.springscania.model.Resposta;

public class RespostaDto {
	@NotBlank
	@NotNull
	private String tipoResposta; 
	@NotBlank
	@NotNull
	private String descResposta;
	@NotNull
	private Long idPergunta;
	
	public void fromResposta(Resposta resposta) {
		this.tipoResposta = resposta.getTipoResposta();
		this.descResposta = resposta.getDescResposta();
		this.idPergunta = resposta.getIdPergunta();
	}

	public String getTipoResposta() {
		return tipoResposta;
	}

	public void setTipoResposta(String tipoResposta) {
		this.tipoResposta = tipoResposta;
	}

	public String getDescResposta() {
		return descResposta;
	}

	public void setDescResposta(String descResposta) {
		this.descResposta = descResposta;
	}

	public Long getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(Long idPergunta) {
		this.idPergunta = idPergunta;
	}
	
}
