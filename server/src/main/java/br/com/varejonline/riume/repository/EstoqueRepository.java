package br.com.varejonline.riume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.varejonline.riume.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

}
