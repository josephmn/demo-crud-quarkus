package service.impl;

import commons.GenericServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.Persona;
import service.api.PersonaServiceApi;

@ApplicationScoped
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Integer> implements PersonaServiceApi {

    @Inject
    EntityManager em;

    public PersonaServiceImpl() {
        super(Persona.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
}
