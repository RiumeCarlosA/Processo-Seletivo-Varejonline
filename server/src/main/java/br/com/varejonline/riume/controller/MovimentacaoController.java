package br.com.varejonline.riume.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.varejonline.riume.dto.ApiErrorDTO;
import br.com.varejonline.riume.dto.request.MovimentacaoCreateDTO;
import br.com.varejonline.riume.dto.response.MovimentacaoResponseDTO;
import br.com.varejonline.riume.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "MovimentacaoController", description = "EndPoints para Movimentacao")
@PreAuthorize("hasAnyRole('ROLE_GERENTE')")
@RequestMapping(value = "/movimentacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovimentacaoController {

	@Autowired
	private MovimentacaoService service;

	// @formatter:off
    @Operation(summary = "Endpoint para buscar informacões básicas de todos os Movimentacaos",
    		   security = @SecurityRequirement(name = "token"))
    @ApiResponses({
    	@ApiResponse(responseCode = "200",
    			description = "Identificação dos Movimentacaos realizadas com sucesso\t\n.",
    			content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
    			schema = @Schema(implementation = MovimentacaoResponseDTO.class))
    	),
    	@ApiResponse(responseCode = "401", 
				description = " Não autorizado - {error.movimentacao.find-all.movimentacao.unauthorized}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "404", 
				description = " Nenhum Movimentacao encontrado - {error.movimentacao.find-all.movimentacao.not-found}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "500", 
				description = " Erro interno - {error.movimentacao.find-all.movimentacao.internal-error}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    })
    
    // @formatter:on
	@GetMapping("")
	public ResponseEntity<List<MovimentacaoResponseDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
    
 // @formatter:off
  	@Operation(summary = "Endpoint para cadastrar um novo Movimentacao.",
   		       security = @SecurityRequirement(name = "token"))
  	@ApiResponses({
  		@ApiResponse(responseCode = "201", 
  	            description = "Movimentacao criado com sucesso.", 
  	            content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
  	                                schema = @Schema(implementation = MovimentacaoCreateDTO.class))
  		),
  		@ApiResponse(responseCode = "401", 
  				description = " Não autorizado - {error.movimentacao.create.movimentacao.unauthorized}\t\n",
  				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
  		),
  		@ApiResponse(responseCode = "403", 
  				description = " Nome já existe - {error.movimentacao.create.movimentacao.name-in-use}\t\n",
  				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
  		),
  		@ApiResponse(responseCode = "500", 
  				description = " Erro interno - {error.movimentacao.create.movimentacao.internal-error}\t\n",
  				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
  		),
  	})
  	// @formatter:on
 	@PostMapping("")
 	public ResponseEntity<MovimentacaoResponseDTO> create(@Valid @RequestBody MovimentacaoCreateDTO objDTO) {
 		MovimentacaoResponseDTO newObj = service.create(objDTO);
 		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
 		return ResponseEntity.created(uri).build();
 	}
}
