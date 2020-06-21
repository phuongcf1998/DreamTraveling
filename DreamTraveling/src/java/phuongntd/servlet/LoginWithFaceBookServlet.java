/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.servlet;

import com.restfb.types.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import phuongntd.user.UserDAO;
import phuongntd.user.UserDTO;
import phuongntd.utils.RandomString;
import phuongntd.utils.RestFB;

/**
 *
 * @author Yun
 */
public class LoginWithFaceBookServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(LoginWithFaceBookServlet.class.getName());
    private final String HOME_MEMBER = "member.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = HOME_MEMBER;
        try {
            String code = request.getParameter("code");

            if (code == null || code.isEmpty()) {

            } else {
                String accessToken = RestFB.getToken(code);
                String password = RandomString.generateRandomString(9);

                User user = RestFB.getUserInfo(accessToken);
                HttpSession session = request.getSession();
                UserDTO dto = new UserDTO(user.getId(), password, user.getName(), 1, 1);
                session.setAttribute("MEMBER", dto);
                UserDAO dao = new UserDAO();
                boolean checkUserExist = dao.checkUserFacebookIsExist(dto.getUserID());
                if (!checkUserExist) {
                    dao.insertUserFaceBookWhenLogin(dto);
                }

            }

        } catch (NamingException ex) {
            log.error("LoginWithFaceBookServlet_NamingException " + ex.getMessage());

        } catch (SQLException ex) {
            log.error("LoginWithFaceBookServlet_SQLException " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
