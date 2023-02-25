package br.com.varejonline.riume.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.varejonline.riume.dto.request.GerenteRequestDTO;
import br.com.varejonline.riume.dto.request.GerenteUpdateDTO;
import br.com.varejonline.riume.dto.response.GerenteResponseDTO;
import br.com.varejonline.riume.exception.NotFoundException;
import br.com.varejonline.riume.model.usuarios.Gerente;
import br.com.varejonline.riume.repository.usuarios.GerenteRepository;
import lombok.NonNull;

public class GerenteService {

	@Autowired
	GerenteRepository repository;
	
	public GerenteResponseDTO convertGerenteToGerenteResponseDTO(Gerente gerente) {
		return GerenteResponseDTO.builder()
						.id(gerente.getId())
						.nome(gerente.getNome())
						.usuario(gerente.getUsuario())
						.perfis(gerente.getPerfil())
						.build();
	}
	
	@Transactional(readOnly = true)
	public List<GerenteResponseDTO> findAll() {
		List<Gerente> list = repository.findAll();
		
		List<GerenteResponseDTO> listDTO = list.stream().map(obj -> (obj.isDeleted()) ? null : convertGerenteToGerenteResponseDTO(obj))
														  .collect(Collectors.toList());
		listDTO.remove(null);
		
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public GerenteResponseDTO findById(@NonNull Integer gerenteId) {
		Optional<Gerente> optConta = repository.findByIdAndDeleted(gerenteId, false);
		if (optConta.isEmpty()) {
			throw new NotFoundException("error.gerente.find.gerente.not-found");
		}
		if (optConta.get().isDeleted()) {
			throw new NotFoundException("error.gerente.find.gerente.is-deleted");
		}
		return convertGerenteToGerenteResponseDTO(optConta.get());
	}
	
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public GerenteResponseDTO create(GerenteRequestDTO objDTO) {
		Gerente newObj = new Gerente(objDTO.getNome(), objDTO.getUsuario(), objDTO.getSenha());
		repository.save(newObj);
		return convertGerenteToGerenteResponseDTO(newObj);
	}
	
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public GerenteResponseDTO update(Integer id, GerenteUpdateDTO objDTO) {
		objDTO.setId(id);
		Gerente oldObj = repository.findById(id)
							.orElseThrow(() -> new NotFoundException("{error.gerente.update.gerente.not-found}"));
		if(!objDTO.getSenha().equals(oldObj.getSenha())) {
			objDTO.setSenha(objDTO.getSenha());
		}
		
		oldObj = new Gerente(objDTO.getNome(), objDTO.getUsuario(), objDTO.getSenha(), objDTO.getPerfil());
		
		repository.save(oldObj);
		
		return convertGerenteToGerenteResponseDTO(oldObj);
	}

	public void delete(@NonNull Integer id) throws Exception {
		
		Gerente obj = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("{error.gerente.delete.gerente.not-found}"));
		obj.setDeleted(true);
		
		repository.save(obj);
	}
	
	
	
	
}
