package it.uniroma3.model.facade;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import it.uniroma3.model.Utente;

@Stateful(name="utenteFacade")
public class UtenteFacade {
	 
	@PersistenceContext(unitName = "clinica-unit", type = PersistenceContextType.EXTENDED)
	 private EntityManager em;
	
	public Utente createUtente(Utente utente){
		em.persist(utente);
		return utente;
	}
	
	public void detachUtente(Utente utente){
		em.detach(utente);
	}
	
	public Utente getUtenteByUsername(String username) {
		TypedQuery<Utente> query = em.createNamedQuery("findUtenteByUsername", Utente.class);
		query.setParameter("username", username);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
