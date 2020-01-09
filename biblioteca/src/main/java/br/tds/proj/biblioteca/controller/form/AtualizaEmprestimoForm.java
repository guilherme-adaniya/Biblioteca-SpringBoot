package br.tds.proj.biblioteca.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.tds.proj.biblioteca.model.Emprestimo;
import br.tds.proj.biblioteca.model.Livro;
import br.tds.proj.biblioteca.model.Usuario;
import br.tds.proj.biblioteca.repository.EmprestimoRepository;
import br.tds.proj.biblioteca.repository.LivroRepository;
import br.tds.proj.biblioteca.repository.UsuarioRepository;

public class AtualizaEmprestimoForm {

	@NotEmpty @NotNull
	private boolean status;

	private String usuario;
	private String livro;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getLivro() {
		return livro;
	}
	public void setLivro(String livro) {
		this.livro = livro;
	}

	public Emprestimo atualizar(Long id, EmprestimoRepository emprestimoRepository,LivroRepository livroRepository, UsuarioRepository usuarioRepository) {
		Emprestimo emprestimo = emprestimoRepository.getOne(id);
		Usuario usuarioU = usuarioRepository.findByNome(usuario);
		Livro livroL = livroRepository.findByTitulo(livro);
		emprestimo.setLivro(livroL);
		emprestimo.setUsuario(usuarioU);
		return null;
	}
	
}
