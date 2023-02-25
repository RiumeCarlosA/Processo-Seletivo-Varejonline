package br.com.varejonline.riume.repository.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.varejonline.riume.model.usuarios.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
