/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.order.detail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import phuongntd.tour.TourDTO;
import phuongntd.utils.DBUtils;

/**
 *
 * @author Yun
 */
public class OrderDetailDAO implements Serializable {

    public boolean insertOrderDetail(Map<TourDTO, Integer> tourOrders, String orderID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        boolean result = false;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                int count = 0;
                for (Map.Entry order : tourOrders.entrySet()) {
                    String sql = "Insert into tbl_OrderDetail(tourID,orderID,quota) values(?,?,?)";
                    TourDTO dto = (TourDTO) order.getKey();
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, dto.getTourID());
                    stm.setString(2, orderID);
                    stm.setInt(3, (int) order.getValue());

                    int row = stm.executeUpdate();
                    if (row > 0) {
                        count++;
                    }

                }
                if (count == tourOrders.size()) {

                    result = true;
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

        return result;
    }

    public int getTotalQuotaTourInOrderDetail(String tourID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        int result = 0;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT SUM(quota) FROM tbl_OrderDetail WHERE tourID =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, tourID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1);
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
