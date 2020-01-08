package br.tds.proj.biblioteca.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

import br.tds.proj.biblioteca.controller.dto.LivroDto;
import br.tds.proj.biblioteca.controller.form.AtualizaLivroForm;
import br.tds.proj.biblioteca.controller.form.LivroForm;
import br.tds.proj.biblioteca.model.Livro;
import br.tds.proj.biblioteca.repository.AutorRepository;
import br.tds.proj.biblioteca.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivrosController {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@GetMapping
	public Page<LivroDto> listarLivros(@RequestParam(required = false) String titulo, 
			 @PageableDefault(sort ="id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		if (titulo == null) {
			Page<Livro> livros = livroRepository.findAll(paginacao);
			return LivroDto.converter(livros);
		} else {
			Page<Livro> livros = livroRepository.findByTitulo(titulo, paginacao);
			return LivroDto.converter(livros);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LivroDto> cadastrarLivro(@RequestBody @Valid LivroForm form, UriComponentsBuilder uriBuilder) {
		Livro livro = form.converter(livroRepository, autorRepository);
		livroRepository.save(livro);
		
		URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).body(new LivroDto(livro));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> detalharLivro(@PathVariable Long id){
		Optional<Livro> livro = livroRepository.findById(id);
		if(livro.isPresent()) {
			return ResponseEntity.ok(new LivroDto(livro.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LivroDto> atualizarLivro(@PathVariable Long id, @RequestBody @Valid AtualizaLivroForm form){
		Optional<Livro> optional = livroRepository.findById(id);
		if(optional.isPresent()) {
			Livro livro = form.atualizar(id, livroRepository, autorRepository);
			return ResponseEntity.ok(new LivroDto(livro));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> removerLivro(@PathVariable Long id){
		Optional<Livro> livro = livroRepository.findById(id);
		if(livro.isPresent()) {
			livroRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
