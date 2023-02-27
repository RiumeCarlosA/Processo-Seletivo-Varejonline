package br.com.varejonline.riume.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.varejonline.riume.dto.ApiErrorDTO;
import br.com.varejonline.riume.dto.request.ProdutoCreateDTO;
import br.com.varejonline.riume.dto.request.ProdutoRequestDTO;
import br.com.varejonline.riume.dto.response.ProdutoResponseDTO;
import br.com.varejonline.riume.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "ProdutoController", description = "EndPoints para Produto")
@RequestMapping(value = "/produto", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	// @formatter:off
    @Operation(summary = "Endpoint para buscar informacões básicas de todos os Produtoes",
    		   security = @SecurityRequirement(name = "token"))
    @ApiResponses({
    	@ApiResponse(responseCode = "200",
    			description = "Identificação dos Produtoes realizadas com sucesso\t\n.",
    			content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
    			schema = @Schema(implementation = ProdutoResponseDTO.class))
    	),
    	@ApiResponse(responseCode = "401", 
				description = " Não autorizado - {error.produto.find-all.produto.unauthorized}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "404", 
				description = " Nenhum Produto encontrado - {error.produto.find-all.produto.not-found}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "500", 
				description = " Erro interno - {error.produto.find-all.produto.internal-error}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    })
    
    // @formatter:on
	@GetMapping("")
	public ResponseEntity<List<ProdutoResponseDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	// @formatter:off
    @Operation(summary = "Endpoint para buscar informacões básicas dos Produtoes",
    		   security = @SecurityRequirement(name = "token"))
    @ApiResponses({
    	@ApiResponse(responseCode = "200",
    			description = "Identificação do Produto realizada com sucesso\t\n.",
    			content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
    			schema = @Schema(implementation = ProdutoResponseDTO.class))
    	),
    	@ApiResponse(responseCode = "401", 
				description = " Não autorizado - {error.produto.find-by-id.produto.unauthorized}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "404", 
				description = " Produto não encontrado - {error.produto.find-by-id.produto.not-found}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "500", 
				description = " Erro interno - {error.produto.find-by-id.produto.internal-error}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    })
    
    // @formatter:on
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable(name = "id", required = true) Integer id)
			throws Exception {
		return ResponseEntity.ok().body(service.findById(id));
	}

	// @formatter:off
 	@Operation(summary = "Endpoint para cadastrar um novo Produto.",
  		       security = @SecurityRequirement(name = "token"))
 	@ApiResponses({
 		@ApiResponse(responseCode = "201", 
 	            description = "Produto criado com sucesso.", 
 	            content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
 	                                schema = @Schema(implementation = ProdutoRequestDTO.class))
 		),
 		@ApiResponse(responseCode = "401", 
 				description = " Não autorizado - {error.produto.create.produto.unauthorized}\t\n",
 				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
 		),
 		@ApiResponse(responseCode = "403", 
 				description = " Nome já existe - {error.produto.create.produto.name-in-use}\t\n",
 				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
 		),
 		@ApiResponse(responseCode = "500", 
 				description = " Erro interno - {error.produto.create.produto.internal-error}\t\n",
 				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
 		),
 	})
 	// @formatter:on
	@PreAuthorize("hasAnyRole('ROLE_GERENTE')")
	@PostMapping("")
	public ResponseEntity<ProdutoResponseDTO> create(@Valid @RequestBody ProdutoCreateDTO objDTO) {
		ProdutoResponseDTO newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// @formatter:off
    @Operation(summary = "Endpoint para deletar um Produto.",
    		   security = @SecurityRequirement(name = "token"))
    @ApiResponses({
    	@ApiResponse(responseCode = "200", 
                description = "Produto deletado com sucesso.", 
                content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Void.class))
    	),
        @ApiResponse(responseCode = "404", 
        		description = " Produto não existe - {error.produto.delete.produto.not-found}\t\n",
        		content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
        ),
    })
    // @formatter:on
	@PreAuthorize("hasAnyRole('ROLE_GERENTE')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id", required = true) Integer id) throws Exception {

		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
