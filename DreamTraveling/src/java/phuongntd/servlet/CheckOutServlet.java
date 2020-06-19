/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import phuongntd.cart.CartObj;
import phuongntd.order.OrderDAO;
import phuongntd.user.UserDTO;
import phuongntd.utils.DateCaculator;
import phuongntd.utils.RandomString;

/**
 *
 * @author Yun
 */
public class CheckOutServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(CheckOutServlet.class.getName());
    private final String HOME_MEMBER = "InitCart";

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
        String url = HOME_MEMBER;
        PrintWriter out = response.getWriter();
        String totalCost = request.getParameter("amt");
        double totalCostParse = Double.parseDouble(totalCost);
        Date currentDate = DateCaculator.getCurrentDate();
        try {

            HttpSession session = request.getSession(false);
            if (session != null) {

                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    UserDTO user = (UserDTO) session.getAttribute("MEMBER");
                    String userID = user.getUserID();
                    OrderDAO dao = new OrderDAO();
                    String orderID = RandomString.generateRandomString(6);
                    boolean saveOrderResult = dao.checkOutOrder(orderID, userID, totalCostParse, currentDate);
                }

            }

        } catch (NamingException ex) {
            log.error("CheckOutServlet_NamingException " + ex.getMessage());

        } catch (SQLException ex) {
            log.error("CheckOutServlet_SQLException " + ex.getMessage());
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
