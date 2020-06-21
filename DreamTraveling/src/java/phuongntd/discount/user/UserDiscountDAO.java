/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.discount.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuongntd.utils.DBUtils;

/**
 *
 * @author Yun
 */
public class UserDiscountDAO implements Serializable {

    public boolean checkCodeUsed(String userID, String code) throws SQLException, NamingException {

        boolean result = false;

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "Select userID from tbl_UserDiscount where userID = ?  and discountCode = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, code);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = true;
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

    public boolean insertCodeDiscountByUser(String userID, String code) throws SQLException, NamingException {

        boolean result = false;

        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "INSERT INTO tbl_UserDiscount(userID,discountCode) VALUES (?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, code);
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
