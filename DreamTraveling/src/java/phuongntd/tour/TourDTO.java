/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.tour;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Yun
 */
public class TourDTO implements Serializable {

    private String tourID;
    private String tourName;
    private Date fromDate;
    private Date toDate;
    private double price;
    private int quota;
    private String imageName;
    private String path;
    private String fromPlace;
    private String toPlace;
    private Date dateImport;
    private int status;

    public TourDTO() {
    }

    public TourDTO(String tourID, String tourName, Date fromDate, Date toDate, double price, int quota, String imageName, String path, String fromPlace, String toPlace, Date dateImport, int status) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.quota = quota;
        this.imageName = imageName;
        this.path = path;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.dateImport = dateImport;
        this.status = status;
    }

    /**
     * @return the tourID
     */
    public String getTourID() {
        return tourID;
    }

    /**
     * @param tourID the tourID to set
     */
    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    /**
     * @return the tourName
     */
    public String getTourName() {
        return tourName;
    }

    /**
     * @param tourName the tourName to set
     */
    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the quota
     */
    public int getQuota() {
        return quota;
    }

    /**
     * @param quota the quota to set
     */
    public void setQuota(int quota) {
        this.quota = quota;
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the fromPlace
     */
    public String getFromPlace() {
        return fromPlace;
    }

    /**
     * @param fromPlace the fromPlace to set
     */
    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    /**
     * @return the toPlace
     */
    public String getToPlace() {
        return toPlace;
    }

    /**
     * @param toPlace the toPlace to set
     */
    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    /**
     * @return the dateImport
     */
    public Date getDateImport() {
        return dateImport;
    }

    /**
     * @param dateImport the dateImport to set
     */
    public void setDateImport(Date dateImport) {
        this.dateImport = dateImport;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TourDTO)) {
            return false;
        }
        TourDTO other = (TourDTO) obj;

        if ((this.tourID == null && other.tourID != null)
                || (this.tourID != null && !this.tourID.equals(other.tourID))) {
            return false;

        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tourID != null ? tourID.hashCode() : 0);
        return hash;
    }

}
