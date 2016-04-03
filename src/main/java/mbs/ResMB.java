/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbs;

import daos.OrderDAO;
import entities.Foodcategory;
import entities.Fooddetail;
import entities.Orderdetail;
import entities.Orders;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import services.ResServiceLocal;

/**
 *
 * @author sharki
 */
@Named
@ViewScoped
public class ResMB implements Serializable {

    private List<Foodcategory> foodCategory;
    private List<Fooddetail> fooddetails;
    private Foodcategory selectedCat;
    private List<Fooddetail> selectedFoodDetails;
    private List<Orderdetail> selectedOrderToRemove;
    private Orders orderInstance;
    private List<Orders> orderses;

    @EJB
    ResServiceLocal resServiceLocal;

    @Inject
    OrdersMB ordersMB;

    public ResMB() {
        resetInstance();
    }
    
    public void resetInstance(){
        foodCategory = new ArrayList<>();
        fooddetails = new ArrayList<>();
        selectedCat = new Foodcategory();
        selectedFoodDetails = new ArrayList<>();
        selectedOrderToRemove = new ArrayList<>();
        orderInstance = new Orders();
        orderses = new ArrayList<Orders>();
        orderInstance.setTotal(0);
        orderInstance.setOrderdetailCollection(new ArrayList<>());
        
    }

    /**
     * @return the foodCategory
     */
    public List<Foodcategory> getFoodCategory() {
        try {
            foodCategory = resServiceLocal.getFoodCategories();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            System.out.println("abdoooo");
        }
        return foodCategory;
    }

    /**
     * @param foodCategory the foodCategory to set
     */
    public void setFoodCategory(List<Foodcategory> foodCategory) {
        this.foodCategory = foodCategory;
    }

    /**
     * @return the fooddetails
     */
    public List<Fooddetail> getFooddetails() {
        return fooddetails;
    }

    /**
     * @param fooddetails the fooddetails to set
     */
    public void setFooddetails(List<Fooddetail> fooddetails) {
        this.fooddetails = fooddetails;
    }

    /**
     * @return the selectedCat
     */
    public Foodcategory getSelectedCat() {
        return selectedCat;
    }

    /**
     * @param selectedCat the selectedCat to set
     */
    public void setSelectedCat(Foodcategory selectedCat) {
        this.selectedCat = selectedCat;
    }

    /**
     * @return the selectedFoodDetails
     */
    public List<Fooddetail> getSelectedFoodDetails() {
        return selectedFoodDetails;
    }

    /**
     * @param selectedFoodDetails the selectedFoodDetails to set
     */
    public void setSelectedFoodDetails(List<Fooddetail> selectedFoodDetails) {
        this.selectedFoodDetails = selectedFoodDetails;
    }

    /**
     * @return the selectedOrderToRemove
     */
    public List<Orderdetail> getSelectedOrderToRemove() {
        return selectedOrderToRemove;
    }

    /**
     * @param selectedOrderToRemove the selectedOrderToRemove to set
     */
    public void setSelectedOrderToRemove(List<Orderdetail> selectedOrderToRemove) {
        this.selectedOrderToRemove = selectedOrderToRemove;
    }

    public void onFoodCatChange(Foodcategory foodcategory) {
        try {
            fooddetails = resServiceLocal.getFoodDetailsOfCat((long) selectedCat.getCatID());
        } catch (Exception ex) {
            Logger.getLogger(ResMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addToOrder() {
//        order.addAll(selectedFoodDetails);
        for (Orderdetail orderdetail : orderInstance.getOrderdetailCollection()) {
            if (selectedFoodDetails.contains(orderdetail.getFoodDetFK())) {
                int no = orderdetail.getQuantity() + 1;
                orderdetail.setQuantity(no);
                selectedFoodDetails.remove(orderdetail.getFoodDetFK());
            }
        }
        for (Fooddetail fooddetail : selectedFoodDetails) {
            Orderdetail orderdetail = new Orderdetail();
            orderdetail.setFoodDetFK(fooddetail);
            orderdetail.setQuantity(1);
            orderInstance.getOrderdetailCollection().add(orderdetail);
        }
    }

    public void removeSelected() {
        for (Orderdetail orderdetail : selectedOrderToRemove) {
            orderInstance.getOrderdetailCollection().remove(orderdetail);
            System.out.println("done");
        }
    }

    public void countTotal() {
        for (Orderdetail orderdetail : (List<Orderdetail>) orderInstance.getOrderdetailCollection()) {
            System.out.println(orderdetail.getFoodDetFK().getName() + "  " + orderdetail.getQuantity());
            orderInstance.setTotal(orderInstance.getTotal() + (orderdetail.getFoodDetFK().getPrice() * orderdetail.getQuantity()));
        }
    }

    public void submitOrder() {

        System.out.println("in");
        try {
            orderInstance.setTimeOfOrder(new Date());
            resServiceLocal.saveOrder(orderInstance);
            //   call push
            sendNotification();
            resetInstance();
        } catch (Exception ex) {
            Logger.getLogger(ResMB.class.getName()).log(Level.SEVERE, null, ex);
        }
//        order.clear();
    }

    /**
     * @return the orderInstance
     */
    public Orders getOrderInstance() {
        return orderInstance;
    }

    /**
     * @param orderInstance the orderInstance to set
     */
    public void setOrderInstance(Orders orderInstance) {
        this.orderInstance = orderInstance;
    }

    /**
     * @return the orderses
     */
    public LazyDataModel<Orders> getOrderses() {
        return ordersMB.getLazyDataModel();
    }

    /**
     * @param orderses the orderses to set
     */
    public void setOrderses(List<Orders> orderses) {
        this.orderses = orderses;
    }

    public void sendNotification() {
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/orderpush", new FacesMessage(orderInstance.getTableNo()));
    }

    public void update() {
        ordersMB.getLazyDataModel();
        RequestContext.getCurrentInstance().update("form");
    }
}
