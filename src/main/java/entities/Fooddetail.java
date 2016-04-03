/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sharki
 */
@Entity
@Table(name = "fooddetail")
@NamedQueries({
    @NamedQuery(name = "Fooddetail.findAll", query = "SELECT f FROM Fooddetail f"),
    @NamedQuery(name = "Fooddetail.findByDetID", query = "SELECT f FROM Fooddetail f WHERE f.detID = :detID"),
    @NamedQuery(name = "Fooddetail.findByName", query = "SELECT f FROM Fooddetail f WHERE f.name = :name"),
    @NamedQuery(name = "Fooddetail.findByPrice", query = "SELECT f FROM Fooddetail f WHERE f.price = :price"),
    @NamedQuery(name = "Fooddetail.findByTimeout", query = "SELECT f FROM Fooddetail f WHERE f.timeout = :timeout"),
    @NamedQuery(name = "Fooddetail.findByAvailable", query = "SELECT f FROM Fooddetail f WHERE f.available = :available")})
public class Fooddetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DetID")
    private Integer detID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private Integer price;
    @Size(max = 45)
    @Column(name = "Timeout")
    private String timeout;
    @Lob
    @Size(max = 16777215)
    @Column(name = "Details")
    private String details;
    @Column(name = "Available")
    private Character available;
    @JoinColumn(name = "CatIDFK", referencedColumnName = "CatID")
    @ManyToOne
    private Foodcategory catIDFK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodDetFK")
    private Collection<Orderdetail> orderdetailCollection;

    public Fooddetail() {
    }

    public Fooddetail(Integer detID) {
        this.detID = detID;
    }

    public Fooddetail(Integer detID, String name) {
        this.detID = detID;
        this.name = name;
    }

    public Integer getDetID() {
        return detID;
    }

    public void setDetID(Integer detID) {
        this.detID = detID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Character getAvailable() {
        return available;
    }

    public void setAvailable(Character available) {
        this.available = available;
    }

    public Foodcategory getCatIDFK() {
        return catIDFK;
    }

    public void setCatIDFK(Foodcategory catIDFK) {
        this.catIDFK = catIDFK;
    }

    public Collection<Orderdetail> getOrderdetailCollection() {
        return orderdetailCollection;
    }

    public void setOrderdetailCollection(Collection<Orderdetail> orderdetailCollection) {
        this.orderdetailCollection = orderdetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detID != null ? detID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fooddetail)) {
            return false;
        }
        Fooddetail other = (Fooddetail) object;
        if ((this.detID == null && other.detID != null) || (this.detID != null && !this.detID.equals(other.detID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fooddetail[ detID=" + detID + " ]";
    }
    
}
