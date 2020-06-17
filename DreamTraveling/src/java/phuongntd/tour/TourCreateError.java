/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.tour;

import java.io.Serializable;

/**
 *
 * @author Yun
 */
public class TourCreateError implements Serializable {

    private String tourIdIsEmpty;
    private String tourIdExceedLength;
    private String tourIdIsExisted;
    private String tourNameIsEmpty;
    private String toDateIsEmpty;
    private String fromDateIsEmpty;
    private String invalidDate;
    private String toPlaceIsEmpty;
    private String fromPlaceIsEmpty;
    private String priceIsEmpty;
    private String priceInvalid;
    private String quotaIsEmpty;
    private String quotaInvalid;

    public TourCreateError() {
    }

    public TourCreateError(String tourIdIsEmpty, String tourIdExceedLength, String tourIdIsExisted, String tourNameIsEmpty, String toDateIsEmpty, String fromDateIsEmpty, String invalidDate, String toPlaceIsEmpty, String fromPlaceIsEmpty, String priceIsEmpty, String priceInvalid, String quotaIsEmpty, String quotaInvalid) {
        this.tourIdIsEmpty = tourIdIsEmpty;
        this.tourIdExceedLength = tourIdExceedLength;
        this.tourIdIsExisted = tourIdIsExisted;
        this.tourNameIsEmpty = tourNameIsEmpty;
        this.toDateIsEmpty = toDateIsEmpty;
        this.fromDateIsEmpty = fromDateIsEmpty;
        this.invalidDate = invalidDate;
        this.toPlaceIsEmpty = toPlaceIsEmpty;
        this.fromPlaceIsEmpty = fromPlaceIsEmpty;
        this.priceIsEmpty = priceIsEmpty;
        this.priceInvalid = priceInvalid;
        this.quotaIsEmpty = quotaIsEmpty;
        this.quotaInvalid = quotaInvalid;
    }

    /**
     * @return the tourIdIsEmpty
     */
    public String getTourIdIsEmpty() {
        return tourIdIsEmpty;
    }

    /**
     * @param tourIdIsEmpty the tourIdIsEmpty to set
     */
    public void setTourIdIsEmpty(String tourIdIsEmpty) {
        this.tourIdIsEmpty = tourIdIsEmpty;
    }

    /**
     * @return the tourIdExceedLength
     */
    public String getTourIdExceedLength() {
        return tourIdExceedLength;
    }

    /**
     * @param tourIdExceedLength the tourIdExceedLength to set
     */
    public void setTourIdExceedLength(String tourIdExceedLength) {
        this.tourIdExceedLength = tourIdExceedLength;
    }

    /**
     * @return the tourIdIsExisted
     */
    public String getTourIdIsExisted() {
        return tourIdIsExisted;
    }

    /**
     * @param tourIdIsExisted the tourIdIsExisted to set
     */
    public void setTourIdIsExisted(String tourIdIsExisted) {
        this.tourIdIsExisted = tourIdIsExisted;
    }

    /**
     * @return the tourNameIsEmpty
     */
    public String getTourNameIsEmpty() {
        return tourNameIsEmpty;
    }

    /**
     * @param tourNameIsEmpty the tourNameIsEmpty to set
     */
    public void setTourNameIsEmpty(String tourNameIsEmpty) {
        this.tourNameIsEmpty = tourNameIsEmpty;
    }

    /**
     * @return the toDateIsEmpty
     */
    public String getToDateIsEmpty() {
        return toDateIsEmpty;
    }

    /**
     * @param toDateIsEmpty the toDateIsEmpty to set
     */
    public void setToDateIsEmpty(String toDateIsEmpty) {
        this.toDateIsEmpty = toDateIsEmpty;
    }

    /**
     * @return the fromDateIsEmpty
     */
    public String getFromDateIsEmpty() {
        return fromDateIsEmpty;
    }

    /**
     * @param fromDateIsEmpty the fromDateIsEmpty to set
     */
    public void setFromDateIsEmpty(String fromDateIsEmpty) {
        this.fromDateIsEmpty = fromDateIsEmpty;
    }

    /**
     * @return the invalidDate
     */
    public String getInvalidDate() {
        return invalidDate;
    }

    /**
     * @param invalidDate the invalidDate to set
     */
    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    /**
     * @return the toPlaceIsEmpty
     */
    public String getToPlaceIsEmpty() {
        return toPlaceIsEmpty;
    }

    /**
     * @param toPlaceIsEmpty the toPlaceIsEmpty to set
     */
    public void setToPlaceIsEmpty(String toPlaceIsEmpty) {
        this.toPlaceIsEmpty = toPlaceIsEmpty;
    }

    /**
     * @return the fromPlaceIsEmpty
     */
    public String getFromPlaceIsEmpty() {
        return fromPlaceIsEmpty;
    }

    /**
     * @param fromPlaceIsEmpty the fromPlaceIsEmpty to set
     */
    public void setFromPlaceIsEmpty(String fromPlaceIsEmpty) {
        this.fromPlaceIsEmpty = fromPlaceIsEmpty;
    }

    /**
     * @return the priceIsEmpty
     */
    public String getPriceIsEmpty() {
        return priceIsEmpty;
    }

    /**
     * @param priceIsEmpty the priceIsEmpty to set
     */
    public void setPriceIsEmpty(String priceIsEmpty) {
        this.priceIsEmpty = priceIsEmpty;
    }

    /**
     * @return the priceInvalid
     */
    public String getPriceInvalid() {
        return priceInvalid;
    }

    /**
     * @param priceInvalid the priceInvalid to set
     */
    public void setPriceInvalid(String priceInvalid) {
        this.priceInvalid = priceInvalid;
    }

    /**
     * @return the quotaIsEmpty
     */
    public String getQuotaIsEmpty() {
        return quotaIsEmpty;
    }

    /**
     * @param quotaIsEmpty the quotaIsEmpty to set
     */
    public void setQuotaIsEmpty(String quotaIsEmpty) {
        this.quotaIsEmpty = quotaIsEmpty;
    }

    /**
     * @return the quotaInvalid
     */
    public String getQuotaInvalid() {
        return quotaInvalid;
    }

    /**
     * @param quotaInvalid the quotaInvalid to set
     */
    public void setQuotaInvalid(String quotaInvalid) {
        this.quotaInvalid = quotaInvalid;
    }

    

}
