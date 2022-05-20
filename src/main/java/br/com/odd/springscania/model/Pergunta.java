package br.com.odd.springscania.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pergunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String descPergunta;
	private String tipoPergunta;
	private Long idPesquisa;
	
	public Pergunta() {
	}
	
	public Pergunta(Long id, String descPergunta, String tipoPergunta, Long idPesquisa) {
		super();
		this.id = id;
		this.descPergunta = descPergunta;
		this.tipoPergunta = tipoPergunta;
		this.idPesquisa = idPesquisa;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
