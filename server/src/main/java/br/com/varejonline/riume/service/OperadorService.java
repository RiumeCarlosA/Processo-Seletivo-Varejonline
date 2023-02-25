package br.com.varejonline.riume.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.varejonline.riume.dto.request.OperadorRequestDTO;
import br.com.varejonline.riume.dto.request.OperadorUpdateDTO;
import br.com.varejonline.riume.dto.response.OperadorResponseDTO;
import br.com.varejonline.riume.exception.NotFoundException;
import br.com.varejonline.riume.model.usuarios.Operador;
import br.com.varejonline.riume.repository.usuarios.OperadorRepository;
import lombok.NonNull;

public class OperadorService {

	@Autowired
	OperadorRepository repository;
	
	public OperadorResponseDTO convertOperadorToOperadorResponseDTO(Operador operador) {
		return OperadorResponseDTO.builder()
						.id(operador.getId())
						.nome(operador.getNome())
						.usuario(operador.getUsuario())
						.perfis(operador.getPerfil())
						.build();
	}
	
	@Transactional(readOnly = true)
	public List<OperadorResponseDTO> findAll() {
		List<Operador> list = repository.findAll();
		
		List<OperadorResponseDTO> listDTO = list.stream().map(obj -> (obj.isDeleted()) ? null : convertOperadorToOperadorResponseDTO(obj))
														  .collect(Collectors.toList());
		listDTO.remove(null);
		
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public OperadorResponseDTO findById(@NonNull Integer operadorId) {
		Optional<Operador> optConta = repository.findByIdAndDeleted(operadorId, false);
		if (optConta.isEmpty()) {
			throw new NotFoundException("error.operador.find.operador.not-found");
		}
		if (optConta.get().isDeleted()) {
			throw new NotFoundException("error.operador.find.operador.is-deleted");
		}
		return convertOperadorToOperadorResponseDTO(optConta.get());
	}
	
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public OperadorResponseDTO create(OperadorRequestDTO objDTO) {
		Operador newObj = new Operador(objDTO.getNome(), objDTO.getUsuario(), objDTO.getSenha());
		repository.save(newObj);
		return convertOperadorToOperadorResponseDTO(newObj);
	}
	
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public OperadorResponseDTO update(Integer id, OperadorUpdateDTO objDTO) {
		objDTO.setId(id);
		Operador oldObj = repository.findById(id)
							.orElseThrow(() -> new NotFoundException("{error.operador.update.operador.not-found}"));
		if(!objDTO.getSenha().equals(oldObj.getSenha())) {
			objDTO.setSenha(objDTO.getSenha());
		}
		
		oldObj = new Operador(objDTO.getNome(), objDTO.getUsuario(), objDTO.getSenha(), objDTO.getPerfil());
		
		repository.save(oldObj);
		
		return convertOperadorToOperadorResponseDTO(oldObj);
	}

	public void delete(@NonNull Integer id) throws Exception {
		
		Operador obj = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("{error.operador.delete.operador.not-found}"));
		obj.setDeleted(true);
		
		repository.save(obj);
	}
	
	
	
	
}
