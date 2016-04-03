/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbs;

import entities.Orders;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import services.OrderServiceLocal;

/**
 *
 * @author sharki
 */
@Named
@ViewScoped
public class OrdersMB implements Serializable {

    @EJB
    OrderServiceLocal orderServiceLocal;

    private List<Orders> orderses;

    private LazyDataModel<Orders> lazyDataModel;

    @PostConstruct
    public void init() {
        lazyDataModel = new LazyDataModel<Orders>() {
            @Override
            public List<Orders> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                try {
                    orderses = orderServiceLocal.getOrders(first, pageSize, sortField, sortOrder, filters);
                    int count = count(sortField, sortOrder, filters);
                    lazyDataModel.setRowCount(count);
                } catch (Exception ex) {
                    Logger.getLogger(OrdersMB.class.getName()).log(Level.SEVERE, null, ex);
                }
                return orderses; //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    public int count(String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Orders> orderslistcount = new ArrayList<>();
        try {
            orderslistcount = orderServiceLocal.getOrders(0, 20, null, null, null);
        } catch (Exception ex) {
            Logger.getLogger(OrdersMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderslistcount.size();
    }

    /**
     * @param orderses the orderses to set
     */
    public void setOrderses(List<Orders> orderses) {
        this.orderses = orderses;
    }

    /**
     * @return the lazyDataModel
     */
    public LazyDataModel<Orders> getLazyDataModel() {
        return lazyDataModel;
    }

    /**
     * @param lazyDataModel the lazyDataModel to set
     */
    public void setLazyDataModel(LazyDataModel<Orders> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

}
