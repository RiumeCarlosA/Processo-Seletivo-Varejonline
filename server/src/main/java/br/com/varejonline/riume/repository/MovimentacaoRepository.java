package br.com.varejonline.riume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.varejonline.riume.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

}
