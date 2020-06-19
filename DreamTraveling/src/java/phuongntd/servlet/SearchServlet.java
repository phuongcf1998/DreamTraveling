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
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import phuongntd.tour.TourDAO;
import phuongntd.tour.TourDTO;
import phuongntd.utils.CheckNumber;

/**
 *
 * @author Yun
 */
public class SearchServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(SearchServlet.class.getName());
    private final String HOME_PAGE = "home.jsp";

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
        String fromPlace = request.getParameter("txtFromPlace");
        String toPlace = request.getParameter("txtToPlace");
        String fromDate = request.getParameter("txtFromDate");
        Date fromDateParse = Date.valueOf(fromDate);
        String toDate = request.getParameter("txtToDate");
        Date toDateParse = Date.valueOf(toDate);
        String price = request.getParameter("price");
        int pageIndex = 1;
        if (request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        int pageSize = 5;
        int endPage = 0;
        String url = HOME_PAGE;

        try {
            if (!price.equals("") && CheckNumber.isNumeric(price)) {
                double priceParse = Double.parseDouble(price);
                TourDAO dao = new TourDAO();
                int countListTour = dao.countTourHavePriceValue(fromDateParse, toDateParse, fromPlace, toPlace, priceParse);

                endPage = countListTour / pageSize;

                if (countListTour % pageSize != 0) {
                    endPage++;
                }
                dao.searchTourHavePriceValue(fromDateParse, toDateParse, fromPlace, toPlace, priceParse, pageIndex, pageSize);
                List<TourDTO> listTour = dao.getListTour();
                request.setAttribute("SEARCH_RESULT", listTour);
                request.setAttribute("TOTAL_PAGE", endPage);
            } else if (price.equals("")) {

                TourDAO dao = new TourDAO();
                int countListTour = dao.countTourWithoutPriceValue(fromDateParse, toDateParse, fromPlace, toPlace);

                endPage = countListTour / pageSize;

                if (countListTour % pageSize != 0) {
                    endPage++;
                }
                dao.searchTourWithoutPriceValue(fromDateParse, toDateParse, fromPlace, toPlace, pageIndex, pageSize);
                List<TourDTO> listTour = dao.getListTour();
                request.setAttribute("SEARCH_RESULT", listTour);
                request.setAttribute("TOTAL_PAGE", endPage);
            } else {
                request.setAttribute("SEARCH_RESULT", "");
                request.setAttribute("TOTAL_PAGE", 0);
            }

        } catch (NamingException ex) {
            log.error("SearchServlet_NamingException " + ex.getMessage());

        } catch (SQLException ex) {
            log.error("SearchServlet_SQLException " + ex.getMessage());
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
