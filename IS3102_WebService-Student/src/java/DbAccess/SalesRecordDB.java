package DbAccess;

import Entity.SalesRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class SalesRecordDB {
    
    public SalesRecordDB() {
    }
    
    public SalesRecord createSalesRecord(Long memberID, double amountPaid) {
        SalesRecord salesRecord = new SalesRecord();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/islandfurniture-it07?zeroDateTimeBehavior=convertToNull&user=root&password=12345");
            String stmt = "INSERT INTO salesrecordentity (member_Id, amountDue, amountPaid, STORE_ID) VALUES (?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, memberID);
            ps.setDouble(2, amountPaid);
            ps.setDouble(3, amountPaid);
            ps.setNull(4, java.sql.Types.BIGINT);
            int rowsCreated = ps.executeUpdate();            
            int salesRecordID = 0;
            
            if (rowsCreated == 0)
                throw new SQLException("Create ECommerce Transaction Record Failed");
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    salesRecordID = generatedKeys.getInt(1);
                    salesRecord.setId(salesRecordID);
                    salesRecord.setAmountDue(amountPaid);
                    salesRecord.setAmountPaid(amountPaid);
                    salesRecord.setMemberId(memberID);
                } else {
                    throw new SQLException("Create ECommerce Transaction Record Failed");
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return salesRecord;
    }
    
}
