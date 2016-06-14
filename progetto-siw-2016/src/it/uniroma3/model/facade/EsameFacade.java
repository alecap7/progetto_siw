package it.uniroma3.model.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.uniroma3.model.Esame;

@Stateless(name="esameFacade")
public class EsameFacade {

	@PersistenceContext(unitName = "clinica-unit")
	private EntityManager em;

	public Esame createEsame(Esame esame){
		em.persist(esame);
		return esame;
	}
	
	public void updateEsame(Esame esame){
		em.merge(esame);
	}

	public Esame getEsameByCodice(String codice) {
		TypedQuery<Esame> query = em.createNamedQuery("findEsameByCodice", Esame.class);
		query.setParameter("codice", codice);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}