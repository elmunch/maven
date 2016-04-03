/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author sharki
 */
@Entity
@Table(name = "orderdetail")
@NamedQueries({
    @NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM Orderdetail o"),
    @NamedQuery(name = "Orderdetail.findByQuantity", query = "SELECT o FROM Orderdetail o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "Orderdetail.findByOrderDetailID", query = "SELECT o FROM Orderdetail o WHERE o.orderDetailID = :orderDetailID")})
public class Orderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Quantity")
    private Integer quantity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderDetailID")
    private Integer orderDetailID;
    @JoinColumn(name = "FoodDet_FK", referencedColumnName = "DetID")
    @ManyToOne(optional = false)
    private Fooddetail foodDetFK;
    @JoinColumn(name = "Order_FK", referencedColumnName = "idOrders")
    @ManyToOne(optional = false)
    private Orders orderFK;

    public Orderdetail() {
    }

    public Orderdetail(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Fooddetail getFoodDetFK() {
        return foodDetFK;
    }

    public void setFoodDetFK(Fooddetail foodDetFK) {
        this.foodDetFK = foodDetFK;
    }

    public Orders getOrderFK() {
        return orderFK;
    }

    public void setOrderFK(Orders orderFK) {
        this.orderFK = orderFK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailID != null ? orderDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderdetail)) {
            return false;
        }
        Orderdetail other = (Orderdetail) object;
        if ((this.foodDetFK.getDetID() == null && other.foodDetFK.getDetID() != null) || (this.foodDetFK.getDetID() != null && !this.foodDetFK.getDetID().equals(other.foodDetFK.getDetID()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Orderdetail[ orderDetailID=" + orderDetailID + " ]";
    }

}
