package br.tds.proj.biblioteca.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.tds.proj.biblioteca.model.Usuario;
import br.tds.proj.biblioteca.repository.UsuarioRepository;

public class AtualizaUsuarioForm {

	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String email;
//	@NotNull @NotEmpty
//	private String senha;
//	
//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setEmail(email);
		usuario.setNome(nome);
		return usuario;
	}
	
//	public Usuario atualizarSenha(Long id, UsuarioRepository usuarioRepository) {
//		Usuario usuario = usuarioRepository.getOne(id);
//		usuario.setSenha(senha);
//		return usuario;
//	}

}
