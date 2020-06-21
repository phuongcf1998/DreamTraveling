/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.user;

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
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String username, String password) throws SQLException, NamingException {
        UserDTO user = null;

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "Select userID , password , fullName , role , status from tbl_User where userID = ? and password = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    user = new UserDTO(userID, password, fullName, role, status);

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

        return user;

    }

    public boolean insertUserFaceBookWhenLogin(UserDTO dto) throws NamingException, SQLException {

        boolean result = false;

        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "Insert into tbl_User(userID,password,fullName,role,status) values(?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getUserID());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setInt(4, dto.getRole());
                stm.setInt(5, dto.getStatus());

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

    public boolean checkUserFacebookIsExist(String userID) throws SQLException, NamingException {
        boolean result = false;

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "Select password , fullName , role , status from tbl_User where userID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
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
