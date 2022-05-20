package br.com.odd.springscania.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pesquisa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nomePesquisa;
	private String temaPesquisa;
	
	public Pesquisa() {
	}
	
	public Pesquisa(Long id, String nomePesquisa, String temaPesquisa) {
		super();
		this.id = id;
		this.nomePesquisa = nomePesquisa;
		this.temaPesquisa = temaPesquisa;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomePesquisa() {
		return nomePesquisa;
	}
	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}
	public String getTemaPesquisa() {
		return temaPesquisa;
	}
	public void setTemaPesquisa(String temaPesquisa) {
		this.temaPesquisa = temaPesquisa;
	}
	
	

}
