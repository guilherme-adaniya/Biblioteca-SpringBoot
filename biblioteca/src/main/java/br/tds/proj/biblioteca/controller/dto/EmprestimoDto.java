package br.tds.proj.biblioteca.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.tds.proj.biblioteca.model.Emprestimo;
import br.tds.proj.biblioteca.model.Livro;
import br.tds.proj.biblioteca.model.Usuario;

public class EmprestimoDto {
	
	private String usuario;
	private String livro;
	
	public EmprestimoDto(Emprestimo emprestimo) {
		this.usuario = emprestimo.getUsuario().getNome();
		this.livro = emprestimo.getLivro().getTitulo();
	}
	
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

	public static List<EmprestimoDto> converter(List<Emprestimo> emprestimos) {
		return emprestimos.stream().map(EmprestimoDto::new).collect(Collectors.toList());
	}
}
