package it.uniroma3.model.facade;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import it.uniroma3.model.Medico;

@Stateful(name="medicoFacade")
public class MedicoFacade {

	@PersistenceContext(unitName = "clinica-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	public Medico createMedico(Medico medico){
		em.persist(medico);
		return medico;
	}

	public void detachMedico(Medico medico){
		em.detach(medico);
	}
	
	public Medico getMedicoByNomeCognome(String nome, String cognome) {
		TypedQuery<Medico> query = em.createNamedQuery("findMedicoByNomeCognome", Medico.class);
		query.setParameter("nome", nome);
		query.setParameter("cognome", cognome);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}