package br.com.inventarioApi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.inventarioApi.event.RecursoCriadoEvent;
import br.com.inventarioApi.model.Pessoa;
import br.com.inventarioApi.repository.PessoaRepository;
import br.com.inventarioApi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@PostMapping
	public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	
	@GetMapping
	public ResponseEntity<?> listar() {
		
		List<Pessoa> pessoa = pessoaService.listar();
		
		return (!pessoa.isEmpty() ? ResponseEntity.ok(pessoa):ResponseEntity.noContent().build());
	}
	
	
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Integer codigo) {
		Pessoa pessoaEncontrada = pessoaRepository.findOne(codigo);
		
		return pessoaEncontrada != null ? ResponseEntity.ok(pessoaEncontrada):ResponseEntity.notFound().build();
	}

}
















