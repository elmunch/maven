/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Fooddetail;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sharki
 */
public class FoodDetailDAO extends BaseDAO<Fooddetail>{

    public FoodDetailDAO(Class<Fooddetail> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public List<Fooddetail> getFoodDetailsOfCat(long id) throws Exception{
        String sql = "SELECT * from fooddetail where `CatIDFK` = " + id + ";";
        return executeNativeQuery(sql);
    }
    
    public List<Fooddetail> getFoodDetailsOfOrder(long id) throws Exception{
        String sql = "select * from fooddetail where DetID in (select orderdetail.FoodDet_FK from orderdetail where Order_FK = " + id + ")";
        return executeNativeQuery(sql);
    }
}
