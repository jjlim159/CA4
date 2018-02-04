package service;

import DbAccess.SalesRecordDB;
import Entity.LineItem;
import DbAccess.LineItemDB;
import Entity.SalesRecord;
import DbAccess.SalesRecord_LineItemDB;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("commerce")
public class ECommerceFacadeREST {

    @Context
    private UriInfo context;

    public ECommerceFacadeREST() {
    }

    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ECommerce
     *
     * @param memberID
     * @param amountPaid
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("createECommerceTransactionRecord")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Response createECommerceTransactionRecord(@QueryParam("memberID")long memberID, @QueryParam("amountPaid")double amountPaid) {
        try {
            SalesRecordDB salesRecordDB = new SalesRecordDB();  //Instantiate sales record utility class object to create sales record
            SalesRecord salesRecord = salesRecordDB.createSalesRecord(memberID, amountPaid);
            
            UriBuilder builder = context.getAbsolutePathBuilder();
            builder.path(Long.toString(salesRecord.getId()));
            
            return Response.created(builder.build()).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("createECommerceLineItemRecord")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Response createECommerceLineItemRecord(@QueryParam("salesRecordID")long salesRecordID, @QueryParam("itemEntityID")long itemEntityID, 
            @QueryParam("quantity")int quantity) {
        try {
            LineItem lineItem = new LineItem();
            LineItemDB lineItemDB = new LineItemDB();       //Instantiate lineItem utility object to create line item record in database
            lineItem = lineItemDB.createLineItemRecord(quantity, itemEntityID);
            
            if (lineItem == null)
                throw new SQLException("Create LineItem Record Failed");
            
            SalesRecord_LineItemDB salesRecordLineItemDB = new SalesRecord_LineItemDB();
            int result = salesRecordLineItemDB.createSalesRecordLineItem(salesRecordID, lineItem.getId());     
            //Create salesrecord_lineitem association record
            
            if (result == 0)
                throw new SQLException("Create SalesRecord_LineItem Record Failed");
            
            lineItemDB.updateItemQuantity(quantity, itemEntityID);      //Update stock quantity in database
            
            UriBuilder builder = context.getAbsolutePathBuilder();
            builder.path(Integer.toString(result));
            
            return Response.created(builder.build()).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
