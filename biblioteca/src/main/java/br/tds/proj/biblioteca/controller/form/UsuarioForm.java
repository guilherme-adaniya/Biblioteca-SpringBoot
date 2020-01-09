package br.tds.proj.biblioteca.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.tds.proj.biblioteca.model.Usuario;
import br.tds.proj.biblioteca.repository.UsuarioRepository;

public class UsuarioForm {
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String senha;
	@NotNull
	private Integer matricula;
	
	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public UsuarioForm() {}
	
	public UsuarioForm(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario converter(UsuarioRepository usuarioRepository) {
		return new Usuario(nome, email, senha, matricula);
	}
}
