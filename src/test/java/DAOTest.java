
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


public class DAOTest {
    
    private DAO myDAO;
    private DataSource myDataSource;
    
    @Before
    public void setUp() throws SQLException, DAOException {
	myDataSource = DataSourceFactory.getDataSource();
	myDAO = new DAO(myDataSource);
    }
    
    @Test
    public void TestListDiscountCode() throws DAOException, SQLException {
        List<DiscountCodeEntity> result = myDAO.ListDiscountCode();
        assertEquals(myDAO.NumberOfDiscountCode(),result.size());
    }
    
    @Test
    public void TestAddDiscountCode() throws DAOException, SQLException {
        DiscountCodeEntity dce = new DiscountCodeEntity('P',0.5f);
        myDAO.AddDiscountCode(dce);
        List<DiscountCodeEntity> result = myDAO.ListDiscountCode();
        assertEquals(myDAO.NumberOfDiscountCode(),result.size());
    }
    
    @Test
    public void TestMAJDiscountTaux() throws DAOException, SQLException {
        myDAO.MAJDiscountTaux('P',0.7f);
        List<DiscountCodeEntity> result = myDAO.ListDiscountCode();
        assertEquals(myDAO.NumberOfDiscountCode(),result.size());
    }
    
    @Test
    public void TestDeleteDiscountCode() throws DAOException, SQLException {
        myDAO.DeleteDiscountCode('P');
        List<DiscountCodeEntity> result = myDAO.ListDiscountCode();
        assertEquals(myDAO.NumberOfDiscountCode(),result.size());
    }
    

}
