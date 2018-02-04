package Entity;

import java.util.Date;

/**
 *
 * @author LIM JING JIE
 */


public class SalesRecord {
    
    private int id;
    private double amountDue;
    private double amountPaid;
    private double amountPaidUsing;
    private Date createdDate;
    private String currency;
    private int loyaltyPointsDeducted;
    private String receiptNo;
    private String posName;
    private String servedByStaff;
    private Long memberId;
    private int storeId;

    public SalesRecord() {
    }
    
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getAmountPaidUsing() {
        return amountPaidUsing;
    }

    public void setAmountPaidUsing(double amountPaidUsing) {
        this.amountPaidUsing = amountPaidUsing;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public String getServedByStaff() {
        return servedByStaff;
    }

    public void setServedByStaff(String servedByStaff) {
        this.servedByStaff = servedByStaff;
    }


    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public int getLoyaltyPointsDeducted() {
        return loyaltyPointsDeducted;
    }

    public void setLoyaltyPointsDeducted(int LoyaltyPointsDeducted) {
        this.loyaltyPointsDeducted = LoyaltyPointsDeducted;
    }

}
