package br.tds.proj.biblioteca.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.tds.proj.biblioteca.controller.dto.EmprestimoDto;
import br.tds.proj.biblioteca.controller.form.AtualizaEmprestimoForm;
import br.tds.proj.biblioteca.controller.form.EmprestimoForm;
import br.tds.proj.biblioteca.model.Emprestimo;
import br.tds.proj.biblioteca.repository.EmprestimoRepository;
import br.tds.proj.biblioteca.repository.LivroRepository;
import br.tds.proj.biblioteca.repository.UsuarioRepository;


@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@GetMapping
	public List<EmprestimoDto> listarEmprestimos(@RequestParam(required = false) Long id){
		List<Emprestimo> emprestimos = emprestimoRepository.findAll();
		return EmprestimoDto.converter(emprestimos);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EmprestimoDto> cadastrarEmprestimo(@RequestBody @Valid EmprestimoForm form, UriComponentsBuilder uriBuilder) {
		Emprestimo emprestimo = form.converter(usuarioRepository, livroRepository);
		emprestimoRepository.save(emprestimo);
		
		URI uri = uriBuilder.path("/emprestimos/{id}").buildAndExpand(emprestimo.getId()).toUri();
		return ResponseEntity.created(uri).body(new EmprestimoDto(emprestimo));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmprestimoDto> detalharEmprestimo(@PathVariable Long id) { 
		Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
		if (emprestimo.isPresent()) {
			return ResponseEntity.ok(new EmprestimoDto(emprestimo.get()));			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EmprestimoDto> atualizarEmprestimo(@PathVariable Long id, @RequestBody @Valid AtualizaEmprestimoForm form) {
		Optional<Emprestimo> optional = emprestimoRepository.findById(id);
		if (optional.isPresent()) {
			Emprestimo emprestimo = form.atualizar(id, emprestimoRepository, livroRepository, usuarioRepository);
			return ResponseEntity.ok(new EmprestimoDto(emprestimo));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerEmprestimo(@PathVariable Long id) {
		Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
		if (emprestimo.isPresent()) {
			emprestimoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
