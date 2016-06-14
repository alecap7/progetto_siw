package it.uniroma3.controller;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import it.uniroma3.controller.utils.MessageUtil;
import it.uniroma3.model.Paziente;
import it.uniroma3.model.facade.PazienteFacade;

@ManagedBean(name="pazienteController")
public class PazienteController{
	private final static String RUOLO = "paziente";
	
	private String username;
    	private String password;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	
	@EJB(beanName="pazienteFacade")
	private PazienteFacade pazienteFacade;
	
	public String createPaziente() {
		Paziente paziente = new Paziente();
		paziente.setUsername(username);
		paziente.setPassword(password);
		paziente.setRuolo(RUOLO);
		paziente.setNome(nome);
		paziente.setCognome(cognome);
		paziente.setDataDiNascita(dataDiNascita);
		this.pazienteFacade.createPaziente(paziente);
		
		MessageUtil.setMessage("Paziente " + username + " registrato con successo!");
		return "/amministrazione/dashboard?faces-redirect=true";
	}
	
	//getters & setters

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
}
