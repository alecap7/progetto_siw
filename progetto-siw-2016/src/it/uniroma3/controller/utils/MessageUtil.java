package it.uniroma3.controller.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtil {
	public static void setMessage(String contenuto){
		FacesMessage msg = new FacesMessage(contenuto);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
}
