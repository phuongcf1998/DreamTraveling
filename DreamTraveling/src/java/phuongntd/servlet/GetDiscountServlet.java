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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import phuongntd.discount.code.DisCountCodeDTO;
import phuongntd.discount.code.DiscountCodeDAO;
import phuongntd.discount.code.DiscountCodeError;
import phuongntd.discount.user.UserDiscountDAO;
import phuongntd.discount.user.UserDiscountDTO;
import phuongntd.utils.DateCaculator;

/**
 *
 * @author Yun
 */
public class GetDiscountServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(GetDiscountServlet.class.getName());
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
        String discountCode = request.getParameter("discountCode");

        DiscountCodeError errors = new DiscountCodeError();
        String url = VIEW_CART_PAGE;
        boolean foundErr = false;
        try {
            if (!discountCode.equals("")) {
                String userID = request.getParameter("userID");
                String cost = request.getParameter("totalPrice");
                Date currentDate = (Date) DateCaculator.getCurrentDate();

                DiscountCodeDAO codeDAO = new DiscountCodeDAO();
                DisCountCodeDTO dto = codeDAO.getDiscountCode(discountCode);
                if (dto == null) {
                    errors.setInvalidCode("Code is invalid");
                    foundErr = true;
                }
                if (dto != null) {
                    if (currentDate.after(dto.getExpireDate())) {
                        errors.setCodeIsExpired("Code is expired");
                        foundErr = true;
                    }
                }
                UserDiscountDAO userDAO = new UserDiscountDAO();
                boolean resultCheck = userDAO.checkCodeUsed(userID, discountCode);
                if (resultCheck) {
                    errors.setCodeUsed("Code is used");
                    foundErr = true;
                }
                if (foundErr) {
                    request.setAttribute("DISCOUNT_ERROR", errors);

                } else {
                    double costParse = Double.parseDouble(cost);
                    double costAfterDiscount = costParse - (costParse * dto.getPercent_discount() / 100);
                    request.setAttribute("DISCOUNT_PERCENT", dto.getPercent_discount());

                    request.setAttribute("DISCOUNT_PRICE", costAfterDiscount);

                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        UserDiscountDTO userDiscountDTO = new UserDiscountDTO(userID, discountCode);
                        session.setAttribute("USER_DISCOUNT", userDiscountDTO);
                        
                    }
                }

            }
        } catch (NamingException ex) {
            log.error("GetDiscountServlet_ NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log.error("GetDiscountServlet_ SQLException " + ex.getMessage());
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
