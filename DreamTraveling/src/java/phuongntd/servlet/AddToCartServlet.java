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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import phuongntd.cart.CartObj;
import phuongntd.order.detail.OrderDetailDAO;
import phuongntd.tour.TourDAO;
import phuongntd.tour.TourDTO;
import phuongntd.utils.DateCaculator;

/**
 *
 * @author Yun
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(AddToCartServlet.class.getName());
    private final String HOME_MEMBER = "InitCartServlet";

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
        String tourID = request.getParameter("txtTourID");
        String url = HOME_MEMBER;
        Date currentDate = DateCaculator.getCurrentDate();
        try {

            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }
            TourDAO tourDAO = new TourDAO();
            TourDTO dto = tourDAO.getTourByID(tourID);
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            int totalQuota = tourDAO.getTotalQuotaInTour(tourID);
            int totalQuotaInOrderDetail = orderDetailDAO.getTotalQuotaTourInOrderDetail(tourID);
            int remainQuota = totalQuota - totalQuotaInOrderDetail;

            if (dto.getToDate().before(currentDate)) {
                request.setAttribute("TOUR_EXPIRED", dto.getTourName() + "-" + dto.getTourID() + "  is expired");

            }
            if (dto.getToDate().after(currentDate)) {

                if (cart.getItems() == null && remainQuota != 0) {
                    cart.addItemToCart(dto);

                    session.setAttribute("CART", cart);
                } else if (cart.getItems() != null && remainQuota != 0) {
                    if (cart.getItems().get(dto) == null) {
                        cart.addItemToCart(dto);
                        session.setAttribute("CART", cart);
                    } else if (cart.getItems().get(dto) < remainQuota && cart.getItems().get(dto) != null) {
                        cart.addItemToCart(dto);

                        session.setAttribute("CART", cart);
                    } else if (cart.getItems().get(dto) >= remainQuota) {
                        request.setAttribute("TOUR_FULL", dto.getTourName() + " Tour is full");
                    }

                } else {
                    request.setAttribute("TOUR_FULL", dto.getTourName() + " Tour is full");
                }
            }

        } catch (NamingException ex) {
            log.error("AddToCartServlet_NamingException " + ex.getMessage());

        } catch (SQLException ex) {
            log.error("AddToCartServlet_SQLException " + ex.getMessage());
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
