package br.com.varejonline.riume.repository.usuarios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.varejonline.riume.model.usuarios.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Optional<Pessoa> findByusuario(String usuario);

}
