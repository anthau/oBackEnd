/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package details;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author antto
 */
@Entity
@Table(name = "routeDetail")
@XmlRootElement
@NamedQueries({
    
  
    @NamedQuery(name = "RouteDetail.find", query = "SELECT r FROM RouteDetail r"),
    @NamedQuery(name = "RouteDetail.findAll", query = "SELECT r FROM RouteDetail r"),
    @NamedQuery(name = "RouteDetail.findById", query = "SELECT r FROM RouteDetail r WHERE r.id = :id"),
    @NamedQuery(name = "RouteDetail.findByCheckpointid", query = "SELECT r FROM RouteDetail r  where r.checkpointid=:check AND r.routeID=:route"  ),
    @NamedQuery(name = "RouteDetail.findByRouteID", query = "SELECT r FROM RouteDetail r WHERE r.routeID = :routeID")})
public class RouteDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "checkpointid")
    private long checkpointid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "routeID")
    private long routeID;

    public RouteDetail() {
    }

    public RouteDetail(Long id) {
        this.id = id;
    }

    public RouteDetail(Long id, long checkpointid, long routeID) {
        this.id = id;
        this.checkpointid = checkpointid;
        this.routeID = routeID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCheckpointid() {
        return checkpointid;
    }

    public void setCheckpointid(long checkpointid) {
        this.checkpointid = checkpointid;
    }

    public long getRouteID() {
        return routeID;
    }

    public void setRouteID(long routeID) {
        this.routeID = routeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
       System.out.println("jes1");
        RouteDetail other = (RouteDetail) object;
        if (other.getCheckpointid()==  this.checkpointid ) {
           
         return true;
        }

        return true;
    }

    @Override
    public String toString() {
        return "details.RouteDetail[ id=" + id + " ]";
    }
    
}
