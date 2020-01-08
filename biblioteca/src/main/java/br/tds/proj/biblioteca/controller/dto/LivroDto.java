package br.tds.proj.biblioteca.controller.dto;

import org.springframework.data.domain.Page;

import br.tds.proj.biblioteca.model.Livro;

public class LivroDto {

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
		this.titulo = livro.getTitulo();
		this.autor = livro.getAutor().getNome();
		this.setGenero(livro.getGenero().toString());
	}
	
	public static Page<LivroDto> converter(Page<Livro> livros) {
		return livros.map(LivroDto::new);
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
