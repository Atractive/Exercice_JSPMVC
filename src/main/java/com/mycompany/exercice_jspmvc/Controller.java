package com.mycompany.exercice_jspmvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {

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
            throws ServletException, IOException, SQLException, DAOException {
        
        try {
            
            DAO myDAO = new DAO(DataSourceFactory.getDataSource());
                       
            String codeS = request.getParameter("code");
            String tauxS = request.getParameter("taux");
            String action = request.getParameter("action");
            String message = "";
            
            if ("ADD".equals(action)) {
                if ("".equals(codeS) || "".equals(tauxS)) {
                    message = "Vous devez remplir les deux champs pour ajouter une remise";
                } else {
                    try {
                        DiscountCodeEntity dce = new DiscountCodeEntity(codeS.charAt(0), Float.parseFloat(tauxS));
                        myDAO.AddDiscountCode(dce);
                    } catch (DAOException de) {
                        myDAO.MAJDiscountTaux(codeS.charAt(0), Float.parseFloat(tauxS));
                    }
                }
            }
            
            if ("DELETE".equals(action)) {
                if ("".equals(codeS)==false){
                    try {
                        myDAO.DeleteDiscountCode(codeS.charAt(0));
                    } catch (DAOException de) {
                        message = "Il est impossible de supprimer le code "+codeS;
                    }
                }
            }
          
            List<DiscountCodeEntity> dcelist = myDAO.ListDiscountCode();
            request.setAttribute("dcelist", dcelist);
            request.setAttribute("message",message);
            
            request.getRequestDispatcher("view.jsp").forward(request, response);
            
        } catch (DAOException ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
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
        try {
            try {
                processRequest(request, response);
            } catch (DAOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            try {
                processRequest(request, response);
            } catch (DAOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
