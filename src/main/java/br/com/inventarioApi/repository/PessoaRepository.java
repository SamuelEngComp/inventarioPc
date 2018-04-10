package br.com.inventarioApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.inventarioApi.model.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	@Query("from Pessoa p Where p.id=:codigo")
	public Pessoa findOne(@Param("codigo") Integer codigo);

}
