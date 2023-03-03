package br.com.varejonline.riume.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.varejonline.riume.dto.request.ProdutoCreateDTO;
import br.com.varejonline.riume.dto.response.ProdutoResponseDTO;
import br.com.varejonline.riume.exception.NotFoundException;
import br.com.varejonline.riume.model.Movimentacao;
import br.com.varejonline.riume.model.Produto;
import br.com.varejonline.riume.model.usuarios.Pessoa;
import br.com.varejonline.riume.repository.MovimentacaoRepository;
import br.com.varejonline.riume.repository.ProdutoRepository;
import br.com.varejonline.riume.repository.usuarios.PessoaRepository;
import lombok.NonNull;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private MovimentacaoRepository repositoryM;
	
	@Autowired
	private PessoaRepository repositoryP;
	
	public ProdutoResponseDTO convertProdutoToProdutoResponseDTO(Produto produto) {
		return ProdutoResponseDTO.builder()
						.id(produto.getId())
						.nome(produto.getNome())
						.codBarra(produto.getCodBarra())
						.qtdMin(produto.getQtdMin())
						.saldo(produto.getSaldo())
						.saldoInicial(produto.getSaldoInicial())
						.dataCriacao(produto.getDataCriacao())
						.build();
	}
	
	@Transactional(readOnly = true)
	public List<ProdutoResponseDTO> findAll() {
		List<Produto> list = repository.findAll();
		
		List<ProdutoResponseDTO> listDTO = list.stream().map(obj -> (obj.isDeleted()) ? null : convertProdutoToProdutoResponseDTO(obj))
														  .collect(Collectors.toList());
		listDTO.remove(null);
		
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public ProdutoResponseDTO findById(@NonNull Integer produtoId) {
		Optional<Produto> optConta = repository.findByIdAndDeleted(produtoId, false);
		if (optConta.isEmpty()) {
			throw new NotFoundException("error.produto.find.produto.not-found");
		}
		if (optConta.get().isDeleted()) {
			throw new NotFoundException("error.produto.find.produto.is-deleted");
		}
		return convertProdutoToProdutoResponseDTO(optConta.get());
	}
	
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public ProdutoResponseDTO create(ProdutoCreateDTO objDTO) {
		Produto newObj = new Produto(objDTO.getNome(), objDTO.getCodBarra(), objDTO.getQtdMin(), objDTO.getSaldoInicial());
		Optional<Pessoa> user = repositoryP.findByUsuario(objDTO.getUsuario());
		if(user.isPresent()) {
			repositoryM.save(new Movimentacao(newObj, user.get()));
		}else {
			new NotFoundException("{error.produto.create.produto.not-found}");
		}
		repository.save(newObj);
		return convertProdutoToProdutoResponseDTO(newObj);
	}

	public void delete(@NonNull Integer id) throws Exception {
		
		Produto obj = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("{error.produto.delete.produto.not-found}"));
		obj.setDeleted(true);
		
		repository.save(obj);
	}
	
}
