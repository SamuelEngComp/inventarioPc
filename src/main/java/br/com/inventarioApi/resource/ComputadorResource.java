package br.com.inventarioApi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.inventarioApi.event.RecursoCriadoEvent;
import br.com.inventarioApi.filter.ComputadorFilter;
import br.com.inventarioApi.model.Computador;
import br.com.inventarioApi.repository.ComputadorRepository;
import br.com.inventarioApi.service.ComputadorService;

@RestController
@RequestMapping("/computadores")
public class ComputadorResource {
	
	@Autowired
	private ComputadorService computadorService;
	
	@Autowired
	private ComputadorRepository ComputadorRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public Page<Computador> listar(ComputadorFilter computadorFilter, Pageable pageable) {
		
		return ComputadorRepository.filtrar(computadorFilter,pageable); 
	}
	
	@PostMapping
	public ResponseEntity<Computador> cadastrar(@Valid @RequestBody Computador computador, HttpServletResponse response) {
		Computador pcSalvo = ComputadorRepository.save(computador);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pcSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pcSalvo);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Computador> buscarPeloCodigo(@PathVariable Integer codigo) {
		Computador pcEncontrado = ComputadorRepository.findOne(codigo);
		return pcEncontrado != null ? ResponseEntity.ok(pcEncontrado):ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Computador> atualizar(@PathVariable Integer codigo, @Valid @RequestBody Computador computador){
		Computador computadorSalvo = computadorService.atualizar(codigo, computador);
		return ResponseEntity.ok(computadorSalvo);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		computadorService.deletar(codigo);
		
	}
	
	
	/*@GetMapping
	public ResponseEntity<?> listarComputadores(){
		List<Computador> computador = computadorService.listarComputadores();
		return (!computador.isEmpty() ? ResponseEntity.ok(computador):ResponseEntity.noContent().build());
	}*/
	
	
	
	
	
	
	
	
	
	
	

}























