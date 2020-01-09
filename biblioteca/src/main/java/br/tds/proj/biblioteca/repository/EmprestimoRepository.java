package br.tds.proj.biblioteca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.tds.proj.biblioteca.model.Emprestimo;
import br.tds.proj.biblioteca.model.Usuario;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

	Page<Emprestimo> findById(Long id, Pageable paginacao);

	Usuario findByUsuario(String usuario);

}
