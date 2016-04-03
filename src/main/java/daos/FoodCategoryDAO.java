/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Foodcategory;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sharki
 */
public class FoodCategoryDAO extends BaseDAO<Foodcategory>{
    
    public FoodCategoryDAO(Class<Foodcategory> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public List<Foodcategory> getFoodCategories() throws Exception{
        String sql = "select * from foodcategory;";
        return executeNativeQuery(sql);
    }
}
