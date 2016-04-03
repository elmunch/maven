/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.OrderDAO;
import daos.OrderDetailDAO;
import entities.Orderdetail;
import entities.Orders;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.SortOrder;

/**
 *
 * @author sharki
 */
@Stateless
public class OrderService implements OrderServiceLocal {
    
    @PersistenceContext(unitName = "RestaurantPU")
    EntityManager em;
    
    @Override
    public List<Orders> getOrders(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) throws Exception {
        List<Orders> orderses = new ArrayList<>();
        orderses = new OrderDAO(Orders.class, em).getOrders(first, pageSize, sortField, sortOrder, filters);
        for (Orders o : orderses) {
            o.setOrderdetailCollection(getOrderDetailByOrderID(o.getIdOrders()));
        }
        
        return orderses;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Orderdetail> getOrderDetailByOrderID(int id) throws Exception {
        return new OrderDetailDAO(Orderdetail.class, em).getOrderDetailByOrderID(id);
    }
}
