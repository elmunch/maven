/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sharki
 */
@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByIdOrders", query = "SELECT o FROM Orders o WHERE o.idOrders = :idOrders"),
    @NamedQuery(name = "Orders.findByTotal", query = "SELECT o FROM Orders o WHERE o.total = :total"),
    @NamedQuery(name = "Orders.findByTableNo", query = "SELECT o FROM Orders o WHERE o.tableNo = :tableNo"),
    @NamedQuery(name = "Orders.findByTimeOfOrder", query = "SELECT o FROM Orders o WHERE o.timeOfOrder = :timeOfOrder"),
    @NamedQuery(name = "Orders.findByDone", query = "SELECT o FROM Orders o WHERE o.done = :done")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrders")
    private Integer idOrders;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Total")
    private int total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tableNo")
    private String tableNo;
    @Column(name = "TimeOfOrder")
    @Temporal(TemporalType.DATE)
    private Date timeOfOrder;
    @Column(name = "Done")
    private Character done;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderFK")
    private List<Orderdetail> orderdetailCollection;
    

    public Orders() {
    }

    public Orders(Integer idOrders) {
        this.idOrders = idOrders;
    }

    public Orders(Integer idOrders, int total, String tableNo) {
        this.idOrders = idOrders;
        this.total = total;
        this.tableNo = tableNo;
    }

    public Integer getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(Integer idOrders) {
        this.idOrders = idOrders;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public Date getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(Date timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public Character getDone() {
        return done;
    }

    public void setDone(Character done) {
        this.done = done;
    }

    public List<Orderdetail> getOrderdetailCollection() {
        return orderdetailCollection;
    }

    public void setOrderdetailCollection(List<Orderdetail> orderdetailCollection) {
        this.orderdetailCollection = orderdetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrders != null ? idOrders.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.idOrders == null && other.idOrders != null) || (this.idOrders != null && !this.idOrders.equals(other.idOrders))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Orders[ idOrders=" + idOrders + " ]";
    }
    
}
