package br.com.odd.springscania.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.odd.springscania.model.Pesquisa;

public class PesquisaDto {
	@NotBlank
	@NotNull
	private String nomePesquisa; 
	@NotBlank
	@NotNull
	private String temaPesquisa;

	public void fromPesquisa(Pesquisa pesquisa) {
		this.nomePesquisa = pesquisa.getNomePesquisa();
		this.temaPesquisa = pesquisa.getTemaPesquisa();
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
