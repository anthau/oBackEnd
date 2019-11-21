/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routedetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author antto
 */
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
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() throws JsonProcessingException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oBackEnd");
        EntityManager em = entityManagerFactory.createEntityManager();
        //    em.getTransaction().begin();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(em.createNativeQuery("SELECT  * FROM routeDetail LEFT JOIN Checkpoints  ON routeDetail.checkpointid=Checkpoints.id  where routeDetail.routeID=1;").getResultList());
     
    }

    /**
     * PUT method for updating or creating an instance of routeDetail
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
