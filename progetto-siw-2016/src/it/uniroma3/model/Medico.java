package it.uniroma3.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"nome", "cognome"})
	)
@NamedQuery(name = "findMedicoByNomeCognome", query = "SELECT m FROM Medico m WHERE m.nome = :nome AND m.cognome = :cognome")
public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	private String specializzazione;
	@OneToMany(mappedBy = "medico")
	private List<Esame> esami;	
	//getters and setters
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setSpecializzazione(String specializzazione) {
		this.specializzazione = specializzazione;
	}
	
	public String getSpecializzazione() {
		return specializzazione;
	}
	
	public void setEsami(List<Esame> esami) {
		this.esami = esami;
	}
	
	public List<Esame> getEsami() {
		return esami;
	}
}
