/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orient.obackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Checkpoints;
import details.RouteDetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author antto
 */
@Path("checkpoint")
public class CheckpointResource {
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CheckpointResource
     */
    public CheckpointResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.orient.obackend.CheckpointResource
     *
     * @return an instance of java.lang.String
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() throws JsonProcessingException {
        //TODO return proper representation object
        Checkpoints s;
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        //    em.getTransaction().begin();

        ObjectMapper mapper = new ObjectMapper();
        
        String jsonString = mapper.writeValueAsString(em.createNamedQuery("Checkpoints.findAll").getResultList());
        return jsonString;
    }

    /**
     * PUT method for updating or creating an instance of CheckpointResource
     *
     * @param check
     * @param content representation for the resource
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putXml(Checkpoints check) {
        String test = check.getName();
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(check);
        em.getTransaction().commit();
        return Response.ok("ok311s=" + test).build();
    }

    //Removes Checkpoint and routedetail
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remove123(Checkpoints check) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        
        List<RouteDetail> list = em.createNamedQuery("RouteDetail.findByCheck").setParameter("check", check.getId()).getResultList();
        
        for (RouteDetail item : list) {
            em.remove(item);
        }
        
        List<Checkpoints> list2 = em.createNamedQuery("Checkpoints.findById").setParameter("id", check.getId()).getResultList();
        em.remove(list2.get(0));
        em.getTransaction().commit();
        
        return Response.ok("ok561s=" + list.size()).build();
        
    }
    
}
