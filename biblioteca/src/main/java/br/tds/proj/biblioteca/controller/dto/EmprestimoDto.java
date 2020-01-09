package br.tds.proj.biblioteca.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.tds.proj.biblioteca.model.Emprestimo;
import br.tds.proj.biblioteca.model.Livro;
import br.tds.proj.biblioteca.model.Usuario;

public class EmprestimoDto {
	
	private Usuario usuario;
	private Livro livro;
	
	public EmprestimoDto(Emprestimo emprestimo) {
		this.usuario = emprestimo.getUsuario();
		this.livro = emprestimo.getLivro();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public static List<EmprestimoDto> converter(List<Emprestimo> emprestimos) {
		return emprestimos.stream().map(EmprestimoDto::new).collect(Collectors.toList());
	}
}
