package it.uniroma3.model.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.uniroma3.model.TipologiaEsame;

@Stateless(name="tipologiaEsameFacade")
public class TipologiaEsameFacade {

	@PersistenceContext(unitName = "clinica-unit")
	private EntityManager em;

	public TipologiaEsame createTipologiaEsame(TipologiaEsame tipologiaEsame){
		em.persist(tipologiaEsame);
		return(tipologiaEsame);
	}

	public TipologiaEsame getTipologiaEsameByCodice(String codice) {
		TypedQuery<TipologiaEsame> query = em.createNamedQuery("findTipologiaByCodice", TipologiaEsame.class);
		query.setParameter("codice", codice);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<TipologiaEsame> getAllTipologiaEsame() {
		TypedQuery<TipologiaEsame> query = em.createNamedQuery("findAllTipologie", TipologiaEsame.class);
		return query.getResultList();
	}
}