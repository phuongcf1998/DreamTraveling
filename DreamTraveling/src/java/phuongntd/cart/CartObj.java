/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.cart;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import phuongntd.tour.TourDTO;
import phuongntd.utils.DateCaculator;

/**
 *
 * @author Yun
 */
public class CartObj implements Serializable {

    private String customerId;
    Map<TourDTO, Integer> items;
    Date date = DateCaculator.getCurrentDate();

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<TourDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(TourDTO item) {
        if (this.items == null) {
            this.items = new HashMap<>();

        }
        int quanity = 1;
        if (this.items.containsKey(item)) {
            quanity = this.items.get(item) + 1;
        }
        this.items.put(item, quanity);
    }

    public void updateItemToCart(String id, int count) {
        if (this.items == null) {
            this.items = new HashMap<>();

        }
        Set<TourDTO> set = this.items.keySet();
        for (TourDTO key : set) {
            if (key.getTourID().equals(id)) {
                if (this.items.containsKey(key)) {
                   
                    this.items.replace(key, count);
                
                    
                }

            }
        }

    }

    public void removeItemFromCart(String id) {
        if (this.items == null) {
            return;
        }

        TourDTO dto = new TourDTO(id, "", date, date, 0, 0, "", "", "", date, 1);
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
