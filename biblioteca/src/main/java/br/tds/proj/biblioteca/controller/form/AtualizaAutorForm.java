package br.tds.proj.biblioteca.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.tds.proj.biblioteca.model.Autor;
import br.tds.proj.biblioteca.repository.AutorRepository;

public class AtualizaAutorForm {

	@NotNull @NotEmpty
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Autor atualizar(Long id, AutorRepository autorRepository) {
		Autor autor = autorRepository.getOne(id);
		autor.setNome(this.nome);
		return autor;
	}
}
