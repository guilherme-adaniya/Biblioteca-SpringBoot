package br.tds.proj.biblioteca.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.tds.proj.biblioteca.controller.dto.AutorDto;
import br.tds.proj.biblioteca.controller.form.AtualizaAutorForm;
import br.tds.proj.biblioteca.controller.form.AutorForm;
import br.tds.proj.biblioteca.model.Autor;
import br.tds.proj.biblioteca.repository.AutorRepository;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorRepository autorRepository;
	
	@GetMapping
	public List<AutorDto> listarAutores(@RequestParam(required = false) String nome){
		List<Autor> autores = autorRepository.findAll();
		return AutorDto.converter(autores);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AutorDto> cadastrarAutor(@RequestBody @Valid AutorForm form, UriComponentsBuilder uriBuilder) {
		Autor autor = form.converter(autorRepository);
		autorRepository.save(autor);
		
		URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).body(new AutorDto(autor));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AutorDto> detalharAutor(@PathVariable Long id) { 
		Optional<Autor> autor = autorRepository.findById(id);
		if (autor.isPresent()) {
			return ResponseEntity.ok(new AutorDto(autor.get()));			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AutorDto> atualizarAutor(@PathVariable Long id, @RequestBody @Valid AtualizaAutorForm form) {
		Optional<Autor> optional = autorRepository.findById(id);
		if (optional.isPresent()) {
			Autor autor = form.atualizar(id, autorRepository);
			return ResponseEntity.ok(new AutorDto(autor));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerAutor(@PathVariable Long id) {
		Optional<Autor> autor = autorRepository.findById(id);
		if (autor.isPresent()) {
			autorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
