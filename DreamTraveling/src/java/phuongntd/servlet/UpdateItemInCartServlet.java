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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import phuongntd.cart.CartObj;
import phuongntd.order.detail.OrderDetailDAO;
import phuongntd.tour.TourDAO;

/**
 *
 * @author Yun
 */
public class UpdateItemInCartServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(UpdateItemInCartServlet.class.getName());
    private final String VIEW_CART_PAGE = "view_cart.jsp";

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

        String txtQuantity = request.getParameter("quantityItem");
        String id = request.getParameter("itemID");
        int quantity = Integer.parseInt(txtQuantity);

        String url = VIEW_CART_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {

                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    TourDAO tourDAO = new TourDAO();
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    int totalQuota = tourDAO.getTotalQuotaInTour(id);
                    int totalQuotaInOrderDetail = orderDetailDAO.getTotalQuotaTourInOrderDetail(id);
                    int remainQuota = totalQuota - totalQuotaInOrderDetail;
                    if (quantity > remainQuota) {
                        request.setAttribute("UPDATE_ITEM_ERROR", "Quota of tour remaining " + remainQuota + " slot");

                    } else {
                        cart.updateItemToCart(id, quantity);

                        session.setAttribute("CART", cart);
                    }
                }

            }
        } catch (NamingException ex) {
            log.error("UpdateItemInCartServlet_NamingException " + ex.getMessage());

        } catch (SQLException ex) {
            log.error("UpdateItemInCartServlet_SQLException " + ex.getMessage());
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
