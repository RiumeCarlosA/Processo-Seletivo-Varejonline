package br.com.varejonline.riume.repository.usuarios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.varejonline.riume.model.usuarios.Operador;
import lombok.NonNull;

public interface OperadorRepository extends JpaRepository<Operador, Integer> {

	Optional<Operador> findByIdAndDeleted(@NonNull Integer atendenteId, boolean b);

}
