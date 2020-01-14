package br.tds.proj.biblioteca.controller;

import java.util.EnumSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tds.proj.biblioteca.model.Genero;

@RestController
@RequestMapping("/generos")
public class GeneroController {

	@GetMapping
	public EnumSet<Genero> listarGeneros() {
		EnumSet<Genero> generoSet = null;
		generoSet = EnumSet.allOf(Genero.class);
		return generoSet;
	}
}
