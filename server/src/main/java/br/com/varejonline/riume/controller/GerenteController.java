package br.com.varejonline.riume.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.varejonline.riume.dto.ApiErrorDTO;
import br.com.varejonline.riume.dto.request.GerenteRequestDTO;
import br.com.varejonline.riume.dto.request.GerenteUpdateDTO;
import br.com.varejonline.riume.dto.response.GerenteResponseDTO;
import br.com.varejonline.riume.service.GerenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "GerenteController", description = "EndPoints para Gerente")
@RequestMapping(value = "/gerente", produces = MediaType.APPLICATION_JSON_VALUE)
public class GerenteController {

	@Autowired
	private GerenteService service;

	// @formatter:off
    @Operation(summary = "Endpoint para buscar informacões básicas de todos os Gerentes"
    		/*, security = @SecurityRequirement(name = "token")*/)
    @ApiResponses({
    	@ApiResponse(responseCode = "200",
    			description = "Identificação dos Gerentes realizadas com sucesso\t\n.",
    			content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
    			schema = @Schema(implementation = GerenteResponseDTO.class))
    	),
    	@ApiResponse(responseCode = "401", 
				description = " Não autorizado - {error.gerente.find-all.gerente.unauthorized}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "404", 
				description = " Nenhum Gerente encontrado - {error.gerente.find-all.gerente.not-found}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "500", 
				description = " Erro interno - {error.gerente.find-all.gerente.internal-error}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    })
    
    // @formatter:on
	@GetMapping("")
	public ResponseEntity<List<GerenteResponseDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	// @formatter:off
    @Operation(summary = "Endpoint para buscar informacões básicas dos Gerentes"
    		/*, security = @SecurityRequirement(name = "token")*/)
    @ApiResponses({
    	@ApiResponse(responseCode = "200",
    			description = "Identificação do Gerente realizada com sucesso\t\n.",
    			content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
    			schema = @Schema(implementation = GerenteResponseDTO.class))
    	),
    	@ApiResponse(responseCode = "401", 
				description = " Não autorizado - {error.gerente.find-by-id.gerente.unauthorized}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "404", 
				description = " Gerente não encontrado - {error.gerente.find-by-id.gerente.not-found}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "500", 
				description = " Erro interno - {error.gerente.find-by-id.gerente.internal-error}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    })
    
    // @formatter:on
	@GetMapping("/{id}")
	public ResponseEntity<GerenteResponseDTO> findById(@PathVariable(name = "id", required = true) Integer id)
			throws Exception {
		return ResponseEntity.ok().body(service.findById(id));
	}

	// @formatter:off
 	@Operation(summary = "Endpoint para cadastrar um novo Gerente." 
  		   /*security = @SecurityRequirement(name = "token")*/)
 	@ApiResponses({
 		@ApiResponse(responseCode = "201", 
 	            description = "Gerente criado com sucesso.", 
 	            content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
 	                                schema = @Schema(implementation = GerenteRequestDTO.class))
 		),
 		@ApiResponse(responseCode = "401", 
 				description = " Não autorizado - {error.gerente.create.gerente.unauthorized}\t\n",
 				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
 		),
 		@ApiResponse(responseCode = "403", 
 				description = " Nome já existe - {error.gerente.create.gerente.name-in-use}\t\n",
 				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
 		),
 		@ApiResponse(responseCode = "500", 
 				description = " Erro interno - {error.gerente.create.gerente.internal-error}\t\n",
 				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
 		),
 	})
 	// @formatter:on
	@PostMapping("")
	public ResponseEntity<GerenteResponseDTO> create(@Valid @RequestBody GerenteRequestDTO objDTO) {
		GerenteResponseDTO newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// @formatter:off
  	@Operation(summary = "Endpoint para Atualizar um Gerente." 
   		   /*security = @SecurityRequirement(name = "token")*/)
  	@ApiResponses({
  		@ApiResponse(responseCode = "201", 
  	            description = "Gerente atualizado com sucesso.", 
  	            content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
  	                                schema = @Schema(implementation = GerenteUpdateDTO.class))
  		),
  		@ApiResponse(responseCode = "401", 
  				description = " Não autorizado - {error.gerente.update.gerente.unauthorized}\t\n",
  				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
  		),
  		@ApiResponse(responseCode = "403", 
  				description = " Nome já existe - {error.gerente.update.gerente.name-in-use}\t\n",
  				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
  		),
  		@ApiResponse(responseCode = "500", 
  				description = " Erro interno - {error.gerente.update.gerente.internal-error}\t\n",
  				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
  		),
  	})
  	// @formatter:on
	@PutMapping("/{id}")
	public ResponseEntity<GerenteResponseDTO> update(@PathVariable(name = "id", required = true) Integer id,
			@Valid @RequestBody GerenteUpdateDTO objDTO) throws Exception {

		service.update(id, objDTO);
		return ResponseEntity.ok().build();
	}

	// @formatter:off
    @Operation(summary = "Endpoint para deletar um Gerente."
    		/*, security = @SecurityRequirement(name = "token")*/)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", 
                description = "Gerente deletado com sucesso.", 
                content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Void.class))
    	),
        @ApiResponse(responseCode = "404", 
        		description = " Gerente não existe - {error.gerente.delete.gerente.not-found}\t\n",
        		content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
        ),
    })
    // @formatter:on
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id", required = true) Integer id) throws Exception {

		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
