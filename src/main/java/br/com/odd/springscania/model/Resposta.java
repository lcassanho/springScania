package br.com.odd.springscania.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resposta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String tipoResposta;
	private String descResposta;
	private Long idPergunta;
	
	public Resposta(Long id, String tipoResposta, String descResposta, Long idPergunta) {
		super();
		this.id = id;
		this.tipoResposta = tipoResposta;
		this.descResposta = descResposta;
		this.idPergunta = idPergunta;
	}
	
	public Resposta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
