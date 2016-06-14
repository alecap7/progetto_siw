package it.uniroma3.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import it.uniroma3.controller.utils.MessageUtil;
import it.uniroma3.model.TipologiaEsame;
import it.uniroma3.model.facade.TipologiaEsameFacade;

@ManagedBean(name="tipologiaEsameController")
@ViewScoped
public class TipologiaEsameController {
	private TipologiaEsame tipologiaEsameCorrente;
	private String codice;
	private String nome;
	private String descrizione;
	private Float costo;
	private String etichettaPrerequisito;
	private String descrizionePrerequisito;
	private String etichettaRisultato;
	
	private List<TipologiaEsame> tipologie;
	
	@EJB(beanName="tipologiaEsameFacade")
	private TipologiaEsameFacade tipologiaEsameFacade;
	
	public String iniziaCreazione(){
		this.tipologiaEsameCorrente = new TipologiaEsame();
		this.tipologiaEsameCorrente.setCodice(codice);
		this.tipologiaEsameCorrente.setNome(nome);
		this.tipologiaEsameCorrente.setDescrizione(descrizione);
		this.tipologiaEsameCorrente.setCosto(costo);
		return null;
	}
	
	public String aggiungiPrerequisito(){
		this.tipologiaEsameCorrente.getPrerequisiti().put(etichettaPrerequisito, descrizionePrerequisito);
		this.etichettaPrerequisito = null;
		this.descrizionePrerequisito = null;
		return null;
	}
	
	public String aggiungiRisultato(){
		this.tipologiaEsameCorrente.getRisultati().add(etichettaRisultato);
		this.etichettaRisultato = null;
		return null;
	}
	
	public String terminaCreazione(){
		this.tipologiaEsameFacade.createTipologiaEsame(tipologiaEsameCorrente);
		MessageUtil.setMessage("Tipologia Esame " + codice + " registrata con successo!");
		return "/amministrazione/dashboard?faces-redirect=true";
	}
	
	public String retrieveTipologie(){
		this.tipologie = this.tipologiaEsameFacade.getAllTipologiaEsame();
		return null;
	}
	
	//getters & setters
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCosto(Float costo) {
		this.costo = costo;
	}
	
	public Float getCosto() {
		return costo;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizionePrerequisito(String descrizionePrerequisito) {
		this.descrizionePrerequisito = descrizionePrerequisito;
	}
	
	public String getDescrizionePrerequisito() {
		return descrizionePrerequisito;
	}
	
	public void setEtichettaPrerequisito(String etichettaPrerequisito) {
		this.etichettaPrerequisito = etichettaPrerequisito;
	}
	
	public String getEtichettaPrerequisito() {
		return etichettaPrerequisito;
	}
	
	public void setEtichettaRisultato(String etichettaRisultato) {
		this.etichettaRisultato = etichettaRisultato;
	}
	
	public String getEtichettaRisultato() {
		return etichettaRisultato;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public TipologiaEsame getTipologiaEsameCorrente() {
		return tipologiaEsameCorrente;
	}
	
	public List<TipologiaEsame> getTipologie() {
		return tipologie;
	}
}
