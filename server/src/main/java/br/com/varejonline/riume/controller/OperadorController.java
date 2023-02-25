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
import br.com.varejonline.riume.dto.request.OperadorRequestDTO;
import br.com.varejonline.riume.dto.request.OperadorUpdateDTO;
import br.com.varejonline.riume.dto.response.OperadorResponseDTO;
import br.com.varejonline.riume.service.OperadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "OperadorController", description = "EndPoints para Operador")
@RequestMapping(value = "/operador", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperadorController {

	@Autowired
	private OperadorService service;
	
	// @formatter:off
    @Operation(summary = "Endpoint para buscar informacões básicas de todos os Operadores"
    		/*, security = @SecurityRequirement(name = "token")*/)
    @ApiResponses({
    	@ApiResponse(responseCode = "200",
    			description = "Identificação dos Operadores realizadas com sucesso\t\n.",
    			content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
    			schema = @Schema(implementation = OperadorResponseDTO.class))
    	),
    	@ApiResponse(responseCode = "401", 
				description = " Não autorizado - {error.operador.find-all.operador.unauthorized}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "404", 
				description = " Nenhum Operador encontrado - {error.operador.find-all.operador.not-found}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "500", 
				description = " Erro interno - {error.operador.find-all.operador.internal-error}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    })
    
    // @formatter:on
	@GetMapping("")
	public ResponseEntity<List<OperadorResponseDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
    
    // @formatter:off
    @Operation(summary = "Endpoint para buscar informacões básicas dos Operadores"
    		/*, security = @SecurityRequirement(name = "token")*/)
    @ApiResponses({
    	@ApiResponse(responseCode = "200",
    			description = "Identificação do Operador realizada com sucesso\t\n.",
    			content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
    			schema = @Schema(implementation = OperadorResponseDTO.class))
    	),
    	@ApiResponse(responseCode = "401", 
				description = " Não autorizado - {error.operador.find-by-id.operador.unauthorized}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "404", 
				description = " Operador não encontrado - {error.operador.find-by-id.operador.not-found}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    	@ApiResponse(responseCode = "500", 
				description = " Erro interno - {error.operador.find-by-id.operador.internal-error}\t\n",
				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
    	),
    })
    
    // @formatter:on
    @GetMapping("/{id}")
	public ResponseEntity<OperadorResponseDTO> findById(@PathVariable(name = "id", required = true) Integer id) throws Exception {
		return ResponseEntity.ok().body(service.findById(id));
	}
    
    // @formatter:off
 	@Operation(summary = "Endpoint para cadastrar um novo Operador." 
  		   /*security = @SecurityRequirement(name = "token")*/)
 	@ApiResponses({
 		@ApiResponse(responseCode = "201", 
 	            description = "Operador criado com sucesso.", 
 	            content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
 	                                schema = @Schema(implementation = OperadorRequestDTO.class))
 		),
 		@ApiResponse(responseCode = "401", 
 				description = " Não autorizado - {error.operador.create.operador.unauthorized}\t\n",
 				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
 		),
 		@ApiResponse(responseCode = "403", 
 				description = " Nome já existe - {error.operador.create.operador.name-in-use}\t\n",
 				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
 		),
 		@ApiResponse(responseCode = "500", 
 				description = " Erro interno - {error.operador.create.operador.internal-error}\t\n",
 				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
 		),
 	})
 	// @formatter:on
 	@PostMapping("")
 	public ResponseEntity<OperadorResponseDTO> create(@Valid @RequestBody OperadorRequestDTO objDTO){
 		OperadorResponseDTO newObj = service.create(objDTO);
 		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
 		return ResponseEntity.created(uri).build();
 	}	
 	
 // @formatter:off
  	@Operation(summary = "Endpoint para Atualizar um Operador." 
   		   /*security = @SecurityRequirement(name = "token")*/)
  	@ApiResponses({
  		@ApiResponse(responseCode = "201", 
  	            description = "Operador atualizado com sucesso.", 
  	            content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
  	                                schema = @Schema(implementation = OperadorUpdateDTO.class))
  		),
  		@ApiResponse(responseCode = "401", 
  				description = " Não autorizado - {error.operador.update.operador.unauthorized}\t\n",
  				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
  		),
  		@ApiResponse(responseCode = "403", 
  				description = " Nome já existe - {error.operador.update.operador.name-in-use}\t\n",
  				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
  		),
  		@ApiResponse(responseCode = "500", 
  				description = " Erro interno - {error.operador.update.operador.internal-error}\t\n",
  				content = @Content(schema = @Schema(implementation = ApiErrorDTO.class))
  		),
  	})
  	// @formatter:on
  	@PutMapping("/{id}")
  	public ResponseEntity<OperadorResponseDTO> update(
  			@PathVariable(name = "id", required = true) Integer id,
  			@Valid @RequestBody OperadorUpdateDTO objDTO) throws Exception {
  		
  		service.update(id, objDTO);
  		return ResponseEntity.ok().build();
  	}	
  	
 // @formatter:off
    @Operation(summary = "Endpoint para deletar um Operador."
    		/*, security = @SecurityRequirement(name = "token")*/)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", 
                description = "Operador deletado com sucesso.", 
                content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Void.class))
    	),
        @ApiResponse(responseCode = "404", 
        		description = " Operador não existe - {error.operador.delete.operador.not-found}\t\n",
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
