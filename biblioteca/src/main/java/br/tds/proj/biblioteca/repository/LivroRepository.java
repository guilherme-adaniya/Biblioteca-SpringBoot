package br.tds.proj.biblioteca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.tds.proj.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	Page<Livro> findByTitulo(String titulo, Pageable paginacao);

	Livro findByTitulo(String livro);

}
