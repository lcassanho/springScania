package br.com.odd.springscania.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bonus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nomeBonus;
	private String descBonus;
	private Long idPesquisa;
	
	public Bonus(Long id, String nomeBonus, String descBonus, Long idPesquisa) {
		super();
		this.id = id;
		this.nomeBonus = nomeBonus;
		this.descBonus = descBonus;
		this.idPesquisa = idPesquisa;
	}
	
	public Bonus() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeBonus() {
		return nomeBonus;
	}
	public void setNomeBonus(String nomeBonus) {
		this.nomeBonus = nomeBonus;
	}
	public String getDescBonus() {
		return descBonus;
	}
	public void setDescBonus(String descBonus) {
		this.descBonus = descBonus;
	}
	public Long getIdPesquisa() {
		return idPesquisa;
	}
	public void setIdPesquisa(Long idPesquisa) {
		this.idPesquisa = idPesquisa;
	}
	
}
