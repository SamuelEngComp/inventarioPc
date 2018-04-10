package br.com.inventarioApi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	
	//atualizar pessoa	
	public Pessoa atualizar(Integer codigo, Pessoa pessoa) {
		
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		
		//copiando as propriedades de pessoa e ignorando o codigo
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		
		//salvando a pessoa atualizada
		return pessoaRepository.save(pessoaSalva);
	}



	//metodo criado para buscar pessoa pelo id
	private Pessoa buscarPessoaPeloCodigo(Integer codigo) {
		
		//buscando a pessoa pelo codigo
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);
		
		if(pessoaSalva == null) {
			
			//se a pessoa vinher nula
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}


	
	//atualizar propriedade ativo
	public void atualizarPropriedadeAtivoPessoa(Integer codigo, Boolean ativo) {
		Pessoa pessoaEncontrada = buscarPessoaPeloCodigo(codigo);
		pessoaEncontrada.setAtivo(ativo);
		
		pessoaRepository.save(pessoaEncontrada);
		
	}
	

}











