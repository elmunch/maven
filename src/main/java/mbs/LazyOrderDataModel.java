package mbs;

import entities.Orders;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sharki
 */
public class LazyOrderDataModel extends LazyDataModel<Orders> {

    private List<Orders> datasource;

    public LazyOrderDataModel(List<Orders> datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<Orders> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        
        return super.load(first, pageSize, sortField, sortOrder, filters); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
