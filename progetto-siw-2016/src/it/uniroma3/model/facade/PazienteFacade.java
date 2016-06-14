package it.uniroma3.model.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.uniroma3.model.Paziente;

@Stateless(name="pazienteFacade")
public class PazienteFacade {
	
	@PersistenceContext(unitName = "clinica-unit")
	private EntityManager em;

	public Paziente createPaziente(Paziente paziente){
		em.persist(paziente);
		return paziente;
	}
	
	public Paziente getPazienteByUsername(String username) {
		TypedQuery<Paziente> query = em.createNamedQuery("findPazienteByUsername", Paziente.class);
		query.setParameter("username", username);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
