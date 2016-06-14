package it.uniroma3.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "findTipologiaByCodice", query = "SELECT t FROM TipologiaEsame t WHERE t.codice = :codice"),
	@NamedQuery(name = "findAllTipologie", query = "SELECT t FROM TipologiaEsame t")
})
public class TipologiaEsame {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique = true)
	private String codice;
	@Column(nullable = false)
	private String nome;
	@Column(length = 2000)
	private String descrizione;
	@Column(nullable = false)
	private Float costo;
	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name="name")
	private Map<String, String> prerequisiti = new HashMap<>();
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> risultati = new LinkedList<>();
		
	//getters and setters
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setCosto(Float costo) {
		this.costo = costo;
	}
	
	public Float getCosto() {
		return costo;
	}
	
	public void setPrerequisiti(Map<String, String> prerequisiti) {
		this.prerequisiti = prerequisiti;
	}
	
	public Map<String, String> getPrerequisiti() {
		return prerequisiti;
	}
	
	public void setRisultati(List<String> risultati) {
		this.risultati = risultati;
	}
	
	public List<String> getRisultati() {
		return risultati;
	}
}
