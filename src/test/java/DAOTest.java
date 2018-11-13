

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.exercice_jspmvc.DAO;
import com.mycompany.exercice_jspmvc.DAOException;
import com.mycompany.exercice_jspmvc.DataSourceFactory;
import com.mycompany.exercice_jspmvc.DiscountCodeEntity;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author crypi
 */
public class DAOTest {
    
    private DAO myDAO;
    private DataSource myDataSource;
    
    @Before
    public void setUp() throws SQLException, DAOException {
	myDataSource = DataSourceFactory.getDataSource();
	myDAO = new DAO(myDataSource);
    }
    
    @Test
    public void TestListDiscountCode() throws DAOException {
        List<DiscountCodeEntity> result = myDAO.ListDiscountCode();
        assertEquals(5,result.size());
    }
    
    @Test
    public void TestAddDiscountCode() throws DAOException {
        DiscountCodeEntity dce = new DiscountCodeEntity('P',(float) 0.5);
        myDAO.AddDiscountCode(dce);
        List<DiscountCodeEntity> result = myDAO.ListDiscountCode();
        assertEquals(5,result.size());
    }
    
    @Test
    public void TestMAJDiscountTaux() throws DAOException {
        myDAO.MAJDiscountTaux('P',(float) 0.5);
        List<DiscountCodeEntity> result = myDAO.ListDiscountCode();
        assertEquals(5,result.size());
    }
    
    @Test
    public void TestDeleteDiscountCode() throws DAOException {
        myDAO.DeleteDiscountCode('P');
        List<DiscountCodeEntity> result = myDAO.ListDiscountCode();
        assertEquals(4,result.size());
    }
    

}
