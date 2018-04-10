package br.com.inventarioApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inventarioApi.model.Computador;
import br.com.inventarioApi.repository.ComputadorRepository;

@Service
public class ComputadorService {
	
	@Autowired
	private ComputadorRepository computadorRepository;
	
	
	//salvando e alterando
	public void salvar(Computador computador) {
		computadorRepository.save(computador);
	}
	
	//listando
	public List<Computador> listar(){
		return computadorRepository.findAll();
	}
	
	//deletando
	public void deletar(Integer codigo) {
		computadorRepository.deleteById(codigo);
	}

}
