/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orient.obackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Routes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author antto
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    //com.orient_oBackEnd_war_1.0-SNAPSHOTPU
    //@PersistenceContext(unitName = "com.orient_oBackEnd_war_1.0-SNAPSHOTPU")
    public GenericResource() {
    }

    @GET
    @Path("/Maps")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getMaps() throws JsonProcessingException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
      
        em.getTransaction().commit();
        Query q=em.createNativeQuery("SELECT distinct map  FROM Routes");
        
        List<String> routes=q.getResultList();
      
        return routes;
    }

    @GET
    @Path("/cities")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCities() throws JsonProcessingException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
      
        em.getTransaction().commit();
        Query q=em.createNativeQuery("SELECT distinct city  FROM Routes");
        
        List<String> routes=q.getResultList();
      
        return routes;
    }

    /**
     * Retrieves representation of an instance of
     * com.orient.obackend.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() throws JsonProcessingException {
        /*
        //TODO return proper representation object  */
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.orient_oBackEnd_war_1.0-SNAPSHOTPU");       

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        //    em.getTransaction().begin();

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(em.createNamedQuery("Routes.findAll").getResultList());

        return jsonString;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param test1
     * @return
     *
     *
     */
    @PUT
    public Response putXml(Routes route) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(route);
        em.getTransaction().commit();
        return Response.ok().entity("Route added").build();
    }

    @DELETE
    public Response deleteRoute(Routes route) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Routes toBeRemoved = em.merge(route);
        em.remove(toBeRemoved);

        // Query query=em.createNativeQuery("SELECT r FROM Routes r WHERE r.id = 1");
        // query.
        System.err.println("jes=" + route.getName());
        em.getTransaction().commit();
        return Response.ok().entity("Route deleted").build();
    }
}
