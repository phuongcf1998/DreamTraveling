/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import phuongntd.user.UserDAO;
import phuongntd.user.UserDTO;

/**
 *
 * @author Yun
 */
public class LoginServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(LoginServlet.class.getName());
    private final String INVALID_PAGE = "invalid.html";
    private final String ACCESS_DENIED_PAGE = "access_denied.html";
    private final String HOME_MEMBER = "member.jsp";
    private final String HOME_ADMIN = "admin.jsp";

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
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        String url = INVALID_PAGE;
        try {
            UserDAO dao = new UserDAO();
            UserDTO dto = dao.checkLogin(username, password);
            

            if (dto != null) {
                if (dto.getStatus() != 2) {
                    switch (dto.getRole()) {
                        case 1: {
                            HttpSession session = request.getSession();
                            session.setAttribute("MEMBER", dto);
                            url = HOME_MEMBER;
                            break;
                        }
                        case 2: {
                            HttpSession session = request.getSession();
                            session.setAttribute("ADMIN", dto);
                            url = HOME_ADMIN;
                            break;
                        }

                        default:
                            url = INVALID_PAGE;
                            break;
                    }
                } else {
                    url = ACCESS_DENIED_PAGE;
                }

            }

        } catch (NamingException ex) {
            log.error("LoginServlet_ NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log.error("LoginServlet_ SQLException " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
