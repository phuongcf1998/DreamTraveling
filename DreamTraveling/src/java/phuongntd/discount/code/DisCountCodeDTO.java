/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.discount.code;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Yun
 */
public class DisCountCodeDTO implements Serializable {
    private String discountCode;
    private int percent_discount;
    private String description;
    private Date expireDate;

    public DisCountCodeDTO() {
    }

    public DisCountCodeDTO(String discountCode, int percent_discount, String description, Date expireDate) {
        this.discountCode = discountCode;
        this.percent_discount = percent_discount;
        this.description = description;
        this.expireDate = expireDate;
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

    /**
     * @return the percent_discount
     */
    public int getPercent_discount() {
        return percent_discount;
    }

    /**
     * @param percent_discount the percent_discount to set
     */
    public void setPercent_discount(int percent_discount) {
        this.percent_discount = percent_discount;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the expireDate
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
    
    
}
