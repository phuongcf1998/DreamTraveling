/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import phuongntd.tour.TourCreateError;
import phuongntd.tour.TourDAO;
import phuongntd.utils.CheckNumber;

/**
 *
 * @author Yun
 */
public class CreateTourServlet extends HttpServlet {

   private static Logger log = Logger.getLogger(CreateTourServlet.class.getName());
    private final String CREATE_ERROR_PAGE = "create_tour.jsp";
    private final String SUCCESS_PAGE = "create_tour_success.html";

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
        String tourName = request.getParameter("txtTourName");
        String fromDate = request.getParameter("txtFromDate");
        String toDate = request.getParameter("txtToDate");
        String fromPlace = request.getParameter("txtFromPlace");
        String toPlace = request.getParameter("txtToPlace");
        String price = request.getParameter("price");
        String quota = request.getParameter("quota");
        String image = request.getParameter("image");
        String url = CREATE_ERROR_PAGE;

        TourCreateError errors = new TourCreateError();
        boolean foundErr = false;
        try {
            if (tourID.equals("")) {
                foundErr = true;
                errors.setTourIdIsEmpty("Tour ID is empty");
            }
            if (tourID.length() > 15) {
                foundErr = true;
                errors.setTourIdExceedLength("Tour ID length must < 15 characters");
            }

            if (tourName.equals("")) {
                foundErr = true;
                errors.setTourNameIsEmpty("Tour Name is empty");
            }
            if (fromDate.equals("")) {
                foundErr = true;
                errors.setFromDateIsEmpty("From Date is empty");
            }

            if (toDate.equals("")) {
                foundErr = true;
                errors.setToDateIsEmpty("To date is empty");
            }

            if (!fromDate.equals("") && !toDate.equals("")) {
                Date date1 = new Date(fromDate);
                Date date2 = new Date(toDate);
                if (date1.after(date2)||date2.before(date1)) {
                    foundErr = true;
                    errors.setInvalidDate("To date and From Date is not valid");
                }
            }
            if (fromPlace.equals("")) {
                foundErr = true;
                errors.setFromPlaceIsEmpty("From Place is empty");
            }
            if (toPlace.equals("")) {
                foundErr = true;
                errors.setToPlaceIsEmpty("To Place is empty");
            }

            if (price.equals("")) {
                foundErr = true;
                errors.setPriceIsEmpty("Price is empty");
            }

            if (!CheckNumber.isNumeric(price) && !price.equals("")) {
                foundErr = true;
                errors.setPriceInvalid("Price must be number");
            }
            if (quota.equals("")) {
                foundErr = true;
                errors.setQuotaIsEmpty("Quota is empty");
            }

            if (!CheckNumber.isNumeric(quota) && !quota.equals("")) {
                foundErr = true;
                errors.setQuotaInvalid("Quota must be number");
            }

            if (foundErr) {
                request.setAttribute("CREATE_TOUR_ERR", errors);

            } else {
                TourDAO dao = new TourDAO();
                java.util.Date utilFromDate = new SimpleDateFormat("MM/dd/yyyy").parse(fromDate);
                java.sql.Date sqlFromDate = new java.sql.Date(utilFromDate.getTime());
                java.util.Date utilToDate = new SimpleDateFormat("MM/dd/yyyy").parse(toDate);
                java.sql.Date sqlToDate = new java.sql.Date(utilToDate.getTime());
                Double priceParse = Double.parseDouble(price);
                int quotaParse = Integer.parseInt(quota);;

                boolean result = dao.createTour(tourID, tourName, sqlFromDate, sqlToDate, priceParse, quotaParse, image, fromPlace, toPlace);
                if (result) {
                    url = SUCCESS_PAGE;
                }
            }

        } catch (SQLException ex) {
            log.error("CreateTourServlet_SQLException " + ex.getMessage());
            if (ex.getMessage().contains("duplicate")) {
                errors.setTourIdIsExisted(tourID + " is existed !!!");
                request.setAttribute("CREATE_TOUR_ERR", errors);
            }

        } catch (NamingException ex) {
            log.error("CreateTourServlet_NamingException " + ex.getMessage());

        } catch (ParseException ex) {
            log.error("CreateTourServlet_ParseException " + ex.getMessage());
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
