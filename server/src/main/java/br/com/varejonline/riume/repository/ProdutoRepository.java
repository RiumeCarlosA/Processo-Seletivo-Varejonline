package br.com.varejonline.riume.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.varejonline.riume.model.Produto;
import lombok.NonNull;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Optional<Produto> findByIdAndDeleted(@NonNull Integer produtoId, boolean b);

}
