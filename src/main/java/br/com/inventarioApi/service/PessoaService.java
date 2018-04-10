package br.com.inventarioApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inventarioApi.model.Pessoa;
import br.com.inventarioApi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	//listando
		public List<Pessoa> listar(){
			return pessoaRepository.findAll();
		}
	
	

}
