package it.uniroma3.controller;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import it.uniroma3.controller.utils.MessageUtil;
import it.uniroma3.model.Utente;
import it.uniroma3.model.facade.UtenteFacade;

@ManagedBean(name="utenteController")
@SessionScoped
public class UtenteController{

	private String username;
	private String password;
	private String ruolo;
	
	private Utente utenteLoggato;

	@EJB(beanName="utenteFacade")
	private UtenteFacade utenteFacade;

	//per il momento mai utilizzato
	public String createUtente() {
		Utente utente = new Utente();
		utente.setUsername(username);
		utente.setPassword(password);
		utente.setRuolo(ruolo);
		utenteFacade.createUtente(utente);
		
		MessageUtil.setMessage("Utente " + username + " registrato con successo!");
		return "/amministrazione/dashboard?faces-redirect=true";
	}

	public String login() {
		this.utenteLoggato = utenteFacade.getUtenteByUsername(username);

		if(this.utenteLoggato!=null && utenteLoggato.getPassword().equals(password)){
			if(utenteLoggato.getRuolo().equals("amministratore"))
				return "amministrazione/dashboard?faces-redirect=true";
			return "/clientela/profilo?faces-redirect=true";
		} else {
			String motivo;
			if(this.utenteLoggato != null)
				motivo = "La password non corrisponde";
			else motivo = "L'utente non esiste";

			MessageUtil.setMessage(motivo);
			return null;
		}
	}
	
	public String logout(){
		this.utenteLoggato = null;
		return "/index?faces-redirect=true";
	}

	@PreDestroy
	public void detachEntities(){
		this.utenteFacade.detachUtente(utenteLoggato);
	}
	
	//getters & setters
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Utente getUtenteLoggato() {
		return utenteLoggato;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	public String getRuolo() {
		return ruolo;
	}
}
