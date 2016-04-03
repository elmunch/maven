/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Orderdetail;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sharki
 */
public class OrderDetailDAO extends BaseDAO<Orderdetail> {

    public OrderDetailDAO(Class<Orderdetail> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public List<Orderdetail> getOrderDetailByOrderID(int id) throws Exception {
        String query = "SELECT * FROM orderdetail WHERE `Order_FK` = " + id;
        return executeNativeQuery(query);
    }

}
