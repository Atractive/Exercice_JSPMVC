package com.mycompany.exercice_jspmvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;


public class DAO {
    
    private final DataSource myDataSource;
    
    public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
    }
    
    public List<DiscountCodeEntity> ListDiscountCode() throws DAOException {
        
        String sql = "SELECT * FROM DISCOUNT_CODE";
        List<DiscountCodeEntity> result = new LinkedList<>();
        
        try (Connection connection = myDataSource.getConnection();
	     PreparedStatement stmt = connection.prepareStatement(sql)) {

		try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
			char code = rs.getString("DISCOUNT_CODE").charAt(0);
			float taux = rs.getFloat("RATE");
			DiscountCodeEntity dce = new DiscountCodeEntity(code,taux);
			result.add(dce);
		    }
		}
                
	}  catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
	}

	return result;
        
    }
    
    public int AddDiscountCode(DiscountCodeEntity DCE) throws DAOException {
        
        String sql = "INSERT INTO DISCOUNT_CODE VALUES(?,?)";
        
        try (Connection connection = myDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
                stmt.setString(1, String.valueOf(DCE.getCode()));
                stmt.setFloat(2, DCE.getTaux());
                
		return stmt.executeUpdate();

	}  catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
	}
    
    }
    
    public int DeleteDiscountCode(char code) throws DAOException {
        
        String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
        
        try (Connection connection = myDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
                
                stmt.setString(1, Character.toString(code));
                
                return stmt.executeUpdate();

	}  catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
	}
    
    }
    
    public int MAJDiscountTaux(char code, float taux) throws DAOException {
        
        String sql = "UPDATE DISCOUNT_CODE SET RATE = ? WHERE DISCOUNT_CODE = ?";
        
        try (Connection connection = myDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
                
                stmt.setFloat(1, taux);
                stmt.setString(2, Character.toString(code));
                
                return stmt.executeUpdate();
                
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }
    }
    
    public int NumberOfDiscountCode() throws SQLException {
        
        int result = 0;
        String sql = "SELECT COUNT(*) AS NUMBER FROM DISCOUNT_CODE";
        
        try (Connection connection = myDataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
                
                if (rs.next()){
                    result = rs.getInt("NUMBER");
                }
                
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
        
        return result;
        
    }
    
}
    
