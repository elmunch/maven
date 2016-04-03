/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Orderdetail;
import entities.Orders;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Order;
import org.primefaces.model.SortOrder;

/**
 *
 * @author sharki
 */
public class OrderDAO extends BaseDAO<Orders> {

    public OrderDAO(Class<Orders> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public List<Orders> getOrders(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) throws Exception {
        String sort = "";
        String order = "";
        if (sortField != null && sortOrder != null) {
            if (sortOrder.compareTo(SortOrder.ASCENDING) == 0) {
                sort = sort + "order by " + sortField + " ASC";
            } else {
                sort = sort + "order by " + sortField + " Desc";
            }

        }
//        if(filters){
//            
//        }
//        String query = "Select * from orders " + sort;
        
        if(sort.equals(""))
            sort = "order by idOrders desc";
        
        String query = "Select * from orders " + sort;

        return executeNativeQuery(query, first, pageSize);
    }

}
