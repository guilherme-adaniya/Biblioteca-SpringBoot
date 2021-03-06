package br.tds.proj.biblioteca.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.tds.proj.biblioteca.model.Autor;
import br.tds.proj.biblioteca.model.Genero;
import br.tds.proj.biblioteca.model.Livro;
import br.tds.proj.biblioteca.repository.AutorRepository;
import br.tds.proj.biblioteca.repository.LivroRepository;

public class AtualizaLivroForm {
	
	@NotNull @NotEmpty
	private String titulo;
	@NotNull @NotEmpty
	private String autor;
	@NotNull @NotEmpty
	private String genero;

	public Livro atualizar(Long id, LivroRepository livroRepository, AutorRepository autorRepository) {
		Autor autorParse = autorRepository.findByNome(autor);
		Genero generoParse = Genero.valueOf(genero);
		
		Livro livro = livroRepository.getOne(id);
		livro.setTitulo(this.titulo);
		livro.setAutor(autorParse);
		livro.setGenero(generoParse);
		return livro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

}
