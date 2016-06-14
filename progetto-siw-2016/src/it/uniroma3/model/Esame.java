package it.uniroma3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "findEsameByCodice", query = "SELECT e FROM Esame e WHERE e.codice = :codice")
public class Esame {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique = true)
	private String codice;
	@Column(nullable = false)
	@OneToOne
	private TipologiaEsame tipologia;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataPrenotazione;
	@Temporal(TemporalType.DATE)
	private Date dataEsame;
	@Column(nullable = false)
	@ManyToOne
	private Paziente paziente;
	@ManyToOne
	private Medico medico;
	@ElementCollection
	@MapKeyColumn(name="name")
	private Map<String, String> risultati = new HashMap<>();

	public Esame() {}

	//getters and setters

	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setTipologia(TipologiaEsame tipologia) {
		this.tipologia = tipologia;
	}

	public TipologiaEsame getTipologia() {
		return tipologia;
	}

	public void setDataPrenotazione(Date dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public Date getDataPrenotazione() {
		return dataPrenotazione;
	}
	
	public void setDataEsame(Date dataEsame) {
		this.dataEsame = dataEsame;
	}
	
	public Date getDataEsame() {
		return dataEsame;
	}
	
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	public Paziente getPaziente() {
		return paziente;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public void setRisultati(Map<String, String> risultati) {
		this.risultati = risultati;
	}
	
	public Map<String, String> getRisultati() {
		return risultati;
	}
}
