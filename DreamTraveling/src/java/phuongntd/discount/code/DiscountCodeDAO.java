/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.discount.code;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuongntd.user.UserDTO;
import phuongntd.utils.DBUtils;

/**
 *
 * @author Yun
 */
public class DiscountCodeDAO implements Serializable {

    public DisCountCodeDTO getDiscountCode(String code) throws SQLException, NamingException {
        DisCountCodeDTO result = null;

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "Select percent_discount , description , expireDate  from tbl_DiscountCode where discountCode = ?  ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, code);

                rs = stm.executeQuery();
                if (rs.next()) {

                    int percent_discount = rs.getInt("percent_discount");
                    String description = rs.getString("description");
                    Date expireDate = rs.getDate("expireDate");
                    result = new DisCountCodeDTO(code, percent_discount, description, expireDate);
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
