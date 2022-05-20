package br.com.odd.springscania.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.odd.springscania.model.Bonus;

public class BonusDto {
	@NotBlank
	@NotNull
	private String nomeBonus; 
	@NotBlank
	@NotNull
	private String descBonus;
	@NotNull
	private Long idPesquisa;
	
	public void fromBonus(Bonus usuario) {
		this.nomeBonus = usuario.getNomeBonus();
		this.descBonus = usuario.getDescBonus();
		this.idPesquisa = usuario.getIdPesquisa();
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
