package DbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author LIM JING JIE
 */


public class SalesRecord_LineItemDB {
    
    public SalesRecord_LineItemDB() {
    }
    
    public int createSalesRecordLineItem(Long salesRecordID, Long lineItemId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/islandfurniture-it07?zeroDateTimeBehavior=convertToNull&user=root&password=12345");
            String stmt = "INSERT INTO salesrecordentity_lineitementity (SalesRecordEntity_ID, itemsPurchased_ID) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps = conn.prepareStatement(stmt);
            ps.setLong(1, salesRecordID);
            ps.setLong(2, lineItemId);
            int rowsCreated = ps.executeUpdate();
            
            if (rowsCreated == 0)
                throw new SQLException("Create SalesRecord_LineItem Record Failed");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return 1;
    }
}
