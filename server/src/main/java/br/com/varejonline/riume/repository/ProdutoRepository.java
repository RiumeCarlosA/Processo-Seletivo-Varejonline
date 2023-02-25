package br.com.varejonline.riume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.varejonline.riume.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
