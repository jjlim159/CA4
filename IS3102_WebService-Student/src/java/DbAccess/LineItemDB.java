package DbAccess;

import Entity.LineItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class LineItemDB {
    
    public LineItemDB() {
    }
    
    public LineItem createLineItemRecord(int quantity, Long itemEntityID) {
        LineItem lineItem = new LineItem();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/islandfurniture-it07?zeroDateTimeBehavior=convertToNull&user=root&password=12345");
            String stmt = "INSERT INTO lineitementity (Quantity, ITEM_ID) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, quantity);
            ps.setLong(2, itemEntityID);
            int rowsCreated = ps.executeUpdate();
            
            if (rowsCreated == 0)
                throw new SQLException("Insert Record Into LineItemEntity Failed");
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long lineItemId = generatedKeys.getLong(1);
                    lineItem.setId(lineItemId);
                    lineItem.setItemId(itemEntityID);
                    lineItem.setQuantity(quantity);
                } else {
                    throw new SQLException("Insert Record Into LineItemEntity Failed");
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lineItem;
    }
    
    public void updateItemQuantity(int quantity, Long itemEntityID) {
        int result = 0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/islandfurniture-it07?zeroDateTimeBehavior=convertToNull&user=root&password=12345");
            String stmt = "UPDATE country_ecommerce c, warehouseentity w, storagebinentity sb, storagebinentity_lineitementity sbli, lineitementity li, itementity i "
                            + "SET li.QUANTITY = li.QUANTITY - ? WHERE li.ITEM_ID=i.ID and sbli.lineItems_ID=li.ID and sb.ID=sbli.StorageBinEntity_ID and " 
                            + "w.id=sb.WAREHOUSE_ID and c.warehouseentity_id=w.id and sb.type<>'Outbound' and i.ID=?;";
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setInt(1, quantity);
            ps.setLong(2, itemEntityID);
            result = ps.executeUpdate();
            
            if (result == 0)
                throw new SQLException("Insert Record Into LineItemEntity Failed");
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
