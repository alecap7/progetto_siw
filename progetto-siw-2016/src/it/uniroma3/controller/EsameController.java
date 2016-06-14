package it.uniroma3.controller;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import it.uniroma3.controller.utils.MessageUtil;
import it.uniroma3.model.Esame;
import it.uniroma3.model.Medico;
import it.uniroma3.model.Paziente;
import it.uniroma3.model.TipologiaEsame;
import it.uniroma3.model.facade.EsameFacade;
import it.uniroma3.model.facade.MedicoFacade;
import it.uniroma3.model.facade.PazienteFacade;
import it.uniroma3.model.facade.TipologiaEsameFacade;


@ManagedBean(name="esameController")
@ViewScoped
public class EsameController{
	private String codice;
	private String codiceTipologia;
	private String usernamePaziente;
	private Date dataEsame;

	private Esame esame;
	private String nomeMedico;
	private String cognomeMedico;

	@EJB(beanName="esameFacade")
	private EsameFacade esameFacade;

	@EJB(beanName="pazienteFacade")
	private PazienteFacade pazienteFacade;

	@EJB(beanName="tipologiaEsameFacade")
	private TipologiaEsameFacade tipologiaEsameFacade;

	@EJB(beanName="medicoFacade")
	private MedicoFacade medicoFacade;

	public String createEsame() {
		Esame esame = new Esame();
		Paziente paziente = this.pazienteFacade.getPazienteByUsername(usernamePaziente);
		TipologiaEsame tipologia = this.tipologiaEsameFacade.getTipologiaEsameByCodice(codiceTipologia);
		esame.setCodice(codice);
		esame.setPaziente(paziente);
		esame.setTipologia(tipologia);
		esame.setDataEsame(dataEsame);
		esame.setDataPrenotazione(new Date());
		this.esameFacade.createEsame(esame);
		MessageUtil.setMessage("Esame " + codice + " registrato con successo!" );
		return "/amministrazione/dashboard?faces-redirect=true";
	}

	public String retrieveEsame(){
		this.esame = this.esameFacade.getEsameByCodice(codice);
		this.esame.setRisultati(new HashMap<>());
		return null;
	}

	public String updateEsame(){
		Medico medico = this.medicoFacade.getMedicoByNomeCognome(nomeMedico, cognomeMedico);
		this.esame.setMedico(medico);
		this.esameFacade.updateEsame(esame);
		MessageUtil.setMessage("Esame " + codice + " aggiornato con successo!" );
		return "/amministrazione/dashboard?faces-redirect=true";
	}

	@PreDestroy
	public void detachEntities(){
		if(esame != null)
			this.medicoFacade.detachMedico(this.esame.getMedico());
	}

	//setters & getters

	public void setCodiceTipologia(String codiceTipologia) {
		this.codiceTipologia = codiceTipologia;
	}

	public String getCodiceTipologia() {
		return codiceTipologia;
	}

	public void setDataEsame(Date dataEsame) {
		this.dataEsame = dataEsame;
	}

	public Date getDataEsame() {
		return dataEsame;
	}

	public void setUsernamePaziente(String usernamePaziente) {
		this.usernamePaziente = usernamePaziente;
	}

	public String getUsernamePaziente() {
		return usernamePaziente;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}

	public Esame getEsame() {
		return esame;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setCognomeMedico(String cognomeMedico) {
		this.cognomeMedico = cognomeMedico;
	}

	public String getCognomeMedico() {
		return cognomeMedico;
	}
}
