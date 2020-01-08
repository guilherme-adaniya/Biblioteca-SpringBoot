package br.tds.proj.biblioteca.controller.dto;

import org.springframework.data.domain.Page;

import br.tds.proj.biblioteca.model.Autor;

public class AutorDto {

	private String nome;

	public String getNome() {
		return nome;
	}
	
	public AutorDto(Autor autor) {
		this.nome = autor.getNome();
	}

	public static Page<AutorDto> converter(Page<Autor> autores) {
		return autores.map(AutorDto::new);
	}
	
}
