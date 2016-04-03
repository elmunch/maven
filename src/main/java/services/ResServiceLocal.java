/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Foodcategory;
import entities.Fooddetail;
import entities.Orders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sharki
 */

@Local
public interface ResServiceLocal {
    
    public List<Foodcategory> getFoodCategories() throws Exception;
    
    public List<Fooddetail> getFoodDetailsOfCat(long id) throws Exception;
    
    public void saveOrder(Orders order) throws Exception;
}
