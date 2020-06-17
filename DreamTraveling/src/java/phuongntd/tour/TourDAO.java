/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.tour;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phuongntd.utils.DBUtils;
import phuongntd.utils.DateCaculator;

/**
 *
 * @author Yun
 */
public class TourDAO implements Serializable {

    private List<TourDTO> listTour;

    public List<TourDTO> getListTour() {
        return listTour;
    }

    public boolean createTour(String tourID, String tourName,
            Date fromDate, Date toDate, double price, int quota, String image, String fromPlace, String toPlace)
            throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement stm = null;
        Date date = (Date) DateCaculator.getCurrentDate();

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "Insert into tbl_Tour(tourID,tourName,fromDate,toDate,price,quota,image,fromPlace,toPlace,dateImport,status)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, tourID);
                stm.setString(2, tourName);
                stm.setDate(3, fromDate);
                stm.setDate(4, toDate);
                stm.setDouble(5, price);
                stm.setInt(6, quota);
                stm.setString(7, image);
                stm.setString(8, fromPlace);
                stm.setString(9, toPlace);
                stm.setDate(10, date);
                stm.setInt(11, 1);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return false;

    }

    public void getAllTour() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "Select tourID,tourName,fromDate,toDate,price,"
                        + "quota,image,fromPlace,toPlace,dateImport,status from tbl_Tour";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (listTour == null) {
                        listTour = new ArrayList<>();
                    }
                    String tourID = rs.getString("tourID");
                    String tourName = rs.getString("tourName");
                    Date fromDate = rs.getDate("fromDate");
                    Date toDate = rs.getDate("toDate");
                    double price = rs.getDouble("price");
                    int quota = rs.getInt("quota");
                    String image = rs.getString("image");
                    String fromPlace = rs.getString("fromPlace");
                    String toPlace = rs.getString("toPlace");
                    Date dateImport = rs.getDate("dateImport");
                    int status = rs.getInt("status");
                    TourDTO dto = new TourDTO(tourID, tourName, fromDate, toDate, price, quota, image, fromPlace, toPlace, dateImport, status);
                    listTour.add(dto);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public TourDTO getTourByID(String tourID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TourDTO result = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "Select tourName,fromDate,toDate,price,"
                        + "quota,image,fromPlace,toPlace,dateImport,status from tbl_Tour where tourID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, tourID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String tourName = rs.getString("tourName");
                    Date fromDate = rs.getDate("fromDate");
                    Date toDate = rs.getDate("toDate");
                    double price = rs.getDouble("price");
                    int quota = rs.getInt("quota");
                    String image = rs.getString("image");
                    String fromPlace = rs.getString("fromPlace");
                    String toPlace = rs.getString("toPlace");
                    Date dateImport = rs.getDate("dateImport");
                    int status = rs.getInt("status");
                    TourDTO dto = new TourDTO(tourID, tourName, fromDate, toDate, price, quota, image, fromPlace, toPlace, dateImport, status);
                    result = dto;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;

    }

}
