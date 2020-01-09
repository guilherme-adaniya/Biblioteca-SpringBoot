package br.tds.proj.biblioteca.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.tds.proj.biblioteca.model.Autor;

public class AutorDto {

	private String nome;

	public String getNome() {
		return nome;
	}
	
	public AutorDto(Autor autor) {
		this.nome = autor.getNome();
	}

	public static List<AutorDto> converter(List<Autor> autores) {
		return autores.stream().map(AutorDto::new).collect(Collectors.toList());
	}
	
}
