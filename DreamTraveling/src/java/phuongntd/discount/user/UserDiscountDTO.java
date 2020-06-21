/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.discount.user;

import java.io.Serializable;

/**
 *
 * @author Yun
 */
public class UserDiscountDTO implements Serializable{
    private String userID;
    private String discountCode;

    public UserDiscountDTO() {
    }

    public UserDiscountDTO(String userID, String discountCode) {
        this.userID = userID;
        this.discountCode = discountCode;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the discountCode
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode the discountCode to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
    
    
}
