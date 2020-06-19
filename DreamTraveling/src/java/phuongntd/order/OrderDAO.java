/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import phuongntd.tour.TourDTO;
import phuongntd.utils.DBUtils;

/**
 *
 * @author Yun
 */
public class OrderDAO implements Serializable {

    public boolean checkOutOrder(String ordersID, String userID, double totalCost, Date createTime) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {

                String sql = "Insert into tbl_Order(orderID ,userID,totalCost,createTime) values(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, ordersID);
                stm.setString(2, userID);
                stm.setDouble(3, totalCost);
                stm.setDate(4, createTime);
                int row = stm.executeUpdate();
                if (row > 0) {
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

}
