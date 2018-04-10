package br.com.inventarioApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.inventarioApi.model.Computador;

@Repository
public interface ComputadorRepository extends JpaRepository<Computador, Integer>{

	
	
	@Query("from Computador pc Where pc.id=:codigo")
	public Computador findOne(@Param("codigo") Integer codigo);

}
