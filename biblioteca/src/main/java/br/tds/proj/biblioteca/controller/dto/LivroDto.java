package br.tds.proj.biblioteca.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.tds.proj.biblioteca.model.Livro;

public class LivroDto {

	private Long id;
	private String titulo;
	private String autor;
	private String genero;
	
	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public LivroDto(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.autor = livro.getAutor().getNome();
		this.setGenero(livro.getGenero().toString());
	}
	
	public static List<LivroDto> converter(List<Livro> livros) {
		return livros.stream().map(LivroDto::new).collect(Collectors.toList());
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
