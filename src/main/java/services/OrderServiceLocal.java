/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Orderdetail;
import entities.Orders;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.primefaces.model.SortOrder;

/**
 *
 * @author sharki
 */
@Local
public interface OrderServiceLocal {

    public List<Orders> getOrders(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) throws Exception;

    public List<Orderdetail> getOrderDetailByOrderID(int id) throws Exception;
}
