package com.mycompany.exercice_jspmvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;


public class DAO {
    
    private final DataSource myDataSource;
    
    public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
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
    
    public int DeleteDiscountCode(DiscountCodeEntity DCE) throws DAOException {
        
        String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
        
        try (Connection connection = myDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
                
                stmt.setInt(1, DCE.getCode());
		
                return stmt.executeUpdate();

	}  catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
	}
    
    }
    
}
    
