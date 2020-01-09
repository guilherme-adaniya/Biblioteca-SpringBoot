package br.tds.proj.biblioteca.controller.form;

import br.tds.proj.biblioteca.model.Emprestimo;
import br.tds.proj.biblioteca.model.Livro;
import br.tds.proj.biblioteca.model.Usuario;
import br.tds.proj.biblioteca.repository.LivroRepository;
import br.tds.proj.biblioteca.repository.UsuarioRepository;

public class EmprestimoForm {
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
	public Emprestimo converter(UsuarioRepository usuarioRepository, LivroRepository livroRepository) {
		Usuario usuarioU = usuarioRepository.findByNome(usuario);
		Livro livroL = livroRepository.findByTitulo(livro);
		return new Emprestimo(usuarioU, livroL);
	}

}
