/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.FoodCategoryDAO;
import daos.FoodDetailDAO;
import daos.OrderDAO;
import daos.OrderDetailDAO;
import entities.Foodcategory;
import entities.Fooddetail;
import entities.Orderdetail;
import entities.Orders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sharki
 */
@Stateless
public class ResService implements ResServiceLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    EntityManager em;

    @Override
    public List<Foodcategory> getFoodCategories() throws Exception {
        return new FoodCategoryDAO(Foodcategory.class, em).getFoodCategories();
    }

    @Override
    public List<Fooddetail> getFoodDetailsOfCat(long id) throws Exception {
        return new FoodDetailDAO(Fooddetail.class, em).getFoodDetailsOfCat(id);
    }

    @Override
    public void saveOrder(Orders order) throws Exception {
        List<Orderdetail> orderdetails = (List<Orderdetail>) order.getOrderdetailCollection();
        order.setOrderdetailCollection(null);
        new OrderDAO(Orders.class, em).saveInstance(order);
        for (Orderdetail orderdetail : orderdetails) {
            orderdetail.setOrderFK(order);
            new OrderDetailDAO(Orderdetail.class, em).saveInstance(orderdetail);
        }
    }

}
