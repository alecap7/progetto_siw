package it.uniroma3.controller;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import it.uniroma3.controller.utils.MessageUtil;
import it.uniroma3.model.Medico;
import it.uniroma3.model.facade.MedicoFacade;

@ManagedBean(name="medicoController")
@ViewScoped
public class MedicoController {
	private String nome;
	private String cognome;
	private String specializzazione;
	
	private Medico medico;
	
	@EJB(beanName="medicoFacade")
	private MedicoFacade medicoFacade;
	
	public String createMedico(){
		this.medico = new Medico();
		medico.setNome(nome);
		medico.setCognome(cognome);
		medico.setSpecializzazione(specializzazione);
		this.medicoFacade.createMedico(medico);
		MessageUtil.setMessage("Medico " + nome + " " + cognome + " registrato con successo!" );
		return "/amministrazione/dashboard?faces-redirect=true";
	}
	
	public String retrieveMedico(){
		this.medico = this.medicoFacade.getMedicoByNomeCognome(nome, cognome);
		return null;
	}
	
	@PreDestroy
	public void detachEntities(){
		this.medicoFacade.detachMedico(medico);
	}
		
	//getters & setters
	
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
	
	public Medico getMedico() {
		return medico;
	}
}
