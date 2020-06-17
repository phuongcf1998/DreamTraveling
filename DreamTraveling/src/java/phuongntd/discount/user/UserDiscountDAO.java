/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.discount.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuongntd.discount.code.DisCountCodeDTO;
import phuongntd.utils.DBUtils;

/**
 *
 * @author Yun
 */
public class UserDiscountDAO implements Serializable {

    public boolean checkCodeUsed(String code, String userID) throws SQLException, NamingException {

        boolean result = false;

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "Select userID from tbl_UserDiscount where discountCode = ?  ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, code);

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
}
