package br.com.odd.springscania.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.odd.springscania.model.Usuario;

public class UsuarioDto {
		@NotBlank
		@NotNull
		private String email; 
		@NotBlank
		@NotNull
		private String senha;
		@NotBlank
		@NotNull
		private String nomeCompleto;	
		@NotBlank
		@NotNull
		private String cpf;
		
		public void fromProduto(Usuario usuario) {
			this.email = usuario.getEmail();
			this.senha = usuario.getSenha();
			this.nomeCompleto = usuario.getNomeCompleto();
			this.cpf = usuario.getCpf();
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public String getNomeCompleto() {
			return nomeCompleto;
		}
		public void setNomeCompleto(String nomeCompleto) {
			this.nomeCompleto = nomeCompleto;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}		
}
