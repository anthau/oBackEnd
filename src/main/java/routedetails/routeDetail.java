/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routedetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Checkpoints;
import details.RouteDetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author antto
 */
class routeParam {

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }
    int route;
}

@Path("details")
public class routeDetail {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of routeDetail
     */
    public routeDetail() {
    }

    /**
     * Retrieves representation of an instance of routedetails.routeDetail
     *
     * @param param
     * @return an instance of java.lang.String
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") String id) throws JsonProcessingException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        System.out.println("search term=" + id);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(em.createNativeQuery("SELECT  * FROM routeDetail LEFT JOIN Checkpoints  ON routeDetail.checkpointid=Checkpoints.id  where routeDetail.routeID=" + id + ";").getResultList());

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(RouteDetail detail) {
        System.err.println("moi");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(detail);
        em.getTransaction().commit();

        return Response.ok().entity("Checkpoint added").build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRoute(RouteDetail detail) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        
    
       List <RouteDetail>a= em.createNamedQuery("Checkpoints.findById").setParameter("route", detail.getRouteID()).setParameter("check", detail.getCheckpointid()).getResultList();

        em.getTransaction().begin();
        em.remove(a.get(0));
        em.getTransaction().commit();
     
        return Response.ok().entity("Checkpoint1 Removed=" + detail.getRouteID()).build();

    }
}
