/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "foodcategory")
@NamedQueries({
    @NamedQuery(name = "Foodcategory.findAll", query = "SELECT f FROM Foodcategory f"),
    @NamedQuery(name = "Foodcategory.findByCatID", query = "SELECT f FROM Foodcategory f WHERE f.catID = :catID"),
    @NamedQuery(name = "Foodcategory.findByName", query = "SELECT f FROM Foodcategory f WHERE f.name = :name")})
public class Foodcategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CatID")
    private Integer catID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "catIDFK")
    private Collection<Fooddetail> fooddetailCollection;

    public Foodcategory() {
    }

    public Foodcategory(Integer catID) {
        this.catID = catID;
    }

    public Foodcategory(Integer catID, String name) {
        this.catID = catID;
        this.name = name;
    }

    public Integer getCatID() {
        return catID;
    }

    public void setCatID(Integer catID) {
        this.catID = catID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Fooddetail> getFooddetailCollection() {
        return fooddetailCollection;
    }

    public void setFooddetailCollection(Collection<Fooddetail> fooddetailCollection) {
        this.fooddetailCollection = fooddetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catID != null ? catID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foodcategory)) {
            return false;
        }
        Foodcategory other = (Foodcategory) object;
        if ((this.catID == null && other.catID != null) || (this.catID != null && !this.catID.equals(other.catID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Foodcategory[ catID=" + catID + " ]";
    }
    
}
