package br.com.inventarioApi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jboss.logging.Message;

@Entity
public class Computador {
	
	@GeneratedValue
	@Id
	private Integer id;
	
	@NotNull(message = "nao pode ser nulo, entrada invalida")
	@Size(min=2,max=20,message="a quantidade de caractere deve esta entre 2 e 20")
	private String nome;
	
	private String numeroSerie;
	
	@NotNull(message = "nao pode ser nulo, entrada invalida")
	@Size(min=2,max=20,message="a quantidade de caractere deve esta entre 2 e 20")
	private String sistemaOperacional;
	
	@NotNull(message = "nao pode ser nulo, entrada invalida")
	@Size(min=2,max=20,message="a quantidade de caractere deve esta entre 2 e 20")
	private String ram;
	
	@NotNull(message = "nao pode ser nulo, entrada invalida")
	@Size(min=2,max=20,message="a quantidade de caractere deve esta entre 2 e 20")
	private String hd;
	
	@NotNull(message = "nao pode ser nulo, entrada invalida")
	@Size(min=2,max=20,message="a quantidade de caractere deve esta entre 2 e 20")
	private String processador;
	
	@NotNull(message = "nao pode ser nulo, entrada invalida")
	@Size(min=2,max=20,message="a quantidade de caractere deve esta entre 2 e 20")
	private String marca;
	
	@NotNull(message = "nao pode ser nulo, entrada invalida")
	@Size(min=2,max=20,message="a quantidade de caractere deve esta entre 2 e 20")
	private String modelo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

	public String getProcessador() {
		return processador;
	}

	public void setProcessador(String processador) {
		this.processador = processador;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hd == null) ? 0 : hd.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numeroSerie == null) ? 0 : numeroSerie.hashCode());
		result = prime * result + ((processador == null) ? 0 : processador.hashCode());
		result = prime * result + ((ram == null) ? 0 : ram.hashCode());
		result = prime * result + ((sistemaOperacional == null) ? 0 : sistemaOperacional.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computador other = (Computador) obj;
		if (hd == null) {
			if (other.hd != null)
				return false;
		} else if (!hd.equals(other.hd))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroSerie == null) {
			if (other.numeroSerie != null)
				return false;
		} else if (!numeroSerie.equals(other.numeroSerie))
			return false;
		if (processador == null) {
			if (other.processador != null)
				return false;
		} else if (!processador.equals(other.processador))
			return false;
		if (ram == null) {
			if (other.ram != null)
				return false;
		} else if (!ram.equals(other.ram))
			return false;
		if (sistemaOperacional == null) {
			if (other.sistemaOperacional != null)
				return false;
		} else if (!sistemaOperacional.equals(other.sistemaOperacional))
			return false;
		return true;
	}
	

}
