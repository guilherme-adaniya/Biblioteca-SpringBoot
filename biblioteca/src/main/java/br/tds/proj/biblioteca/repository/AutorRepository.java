package br.tds.proj.biblioteca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.tds.proj.biblioteca.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

	Page<Autor> findByNome(String nome, Pageable paginacao);

	Autor findByNome(String nome);

}
