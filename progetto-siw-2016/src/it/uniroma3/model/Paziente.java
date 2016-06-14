package it.uniroma3.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "findPazienteByUsername", query = "SELECT p FROM Paziente p WHERE p.username = :username")
public class Paziente extends Utente {
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataDiNascita;
	
	@OneToMany(mappedBy = "paziente")
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
	
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	
	public void setEsami(List<Esame> esami) {
		this.esami = esami;
	}
	
	public List<Esame> getEsami() {
		return esami;
	}
}
