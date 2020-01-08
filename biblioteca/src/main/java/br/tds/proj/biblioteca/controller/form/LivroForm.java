package br.tds.proj.biblioteca.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.tds.proj.biblioteca.model.Autor;
import br.tds.proj.biblioteca.model.Genero;
import br.tds.proj.biblioteca.model.Livro;
import br.tds.proj.biblioteca.repository.AutorRepository;
import br.tds.proj.biblioteca.repository.LivroRepository;

public class LivroForm {

	@NotNull @NotEmpty
	private String titulo;
	@NotNull @NotEmpty
	private String autor;
	@NotNull @NotEmpty
	private String genero;
	
	public Livro converter(LivroRepository livroRepository, AutorRepository autorRepository){
		Autor autorA = autorRepository.findByNome(autor);
		Genero generoG = Genero.valueOf(genero);
		return new Livro(titulo, autorA, generoG);
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
