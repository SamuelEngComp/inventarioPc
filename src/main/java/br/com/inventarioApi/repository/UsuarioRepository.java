package br.com.inventarioApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inventarioApi.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	
	
	public Optional<Usuario> findByEmail(String email);

}
