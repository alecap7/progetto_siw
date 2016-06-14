package it.uniroma3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(name = "findUtenteByUsername", query = "SELECT u FROM Utente u WHERE u.username = :username")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String ruolo;
	
	public Utente() {}
	
	public Utente(String username, String password, String ruolo) {
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
	}
	
	//getters and setters
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
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