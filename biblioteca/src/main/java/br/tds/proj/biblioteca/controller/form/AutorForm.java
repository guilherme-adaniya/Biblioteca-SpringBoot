package br.tds.proj.biblioteca.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.tds.proj.biblioteca.model.Autor;
import br.tds.proj.biblioteca.repository.AutorRepository;

public class AutorForm {

	@NotNull @NotEmpty
	private String nome;
	
	public Autor converter(AutorRepository autorRepository) {
		Autor autor = autorRepository.findByNome(nome);
		return new Autor(nome, autor);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
