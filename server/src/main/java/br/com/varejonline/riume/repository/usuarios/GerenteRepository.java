package br.com.varejonline.riume.repository.usuarios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.varejonline.riume.model.usuarios.Gerente;
import lombok.NonNull;

public interface GerenteRepository extends JpaRepository<Gerente, Integer>{

	Optional<Gerente> findByIdAndDeleted(@NonNull Integer gerenteId, boolean b);

}
