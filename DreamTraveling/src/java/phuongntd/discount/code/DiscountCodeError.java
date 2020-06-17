/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.discount.code;

import java.io.Serializable;

/**
 *
 * @author Yun
 */
public class DiscountCodeError implements Serializable{
    private String invalidCode;
    private String codeIsExpired;
    private String codeUsed;

    public DiscountCodeError() {
    }

    public DiscountCodeError(String invalidCode, String codeIsExpired, String codeUsed) {
        this.invalidCode = invalidCode;
        this.codeIsExpired = codeIsExpired;
        this.codeUsed = codeUsed;
    }

    /**
     * @return the invalidCode
     */
    public String getInvalidCode() {
        return invalidCode;
    }

    /**
     * @param invalidCode the invalidCode to set
     */
    public void setInvalidCode(String invalidCode) {
        this.invalidCode = invalidCode;
    }

    /**
     * @return the codeIsExpired
     */
    public String getCodeIsExpired() {
        return codeIsExpired;
    }

    /**
     * @param codeIsExpired the codeIsExpired to set
     */
    public void setCodeIsExpired(String codeIsExpired) {
        this.codeIsExpired = codeIsExpired;
    }

    /**
     * @return the codeUsed
     */
    public String getCodeUsed() {
        return codeUsed;
    }

    /**
     * @param codeUsed the codeUsed to set
     */
    public void setCodeUsed(String codeUsed) {
        this.codeUsed = codeUsed;
    }

    
    
}
