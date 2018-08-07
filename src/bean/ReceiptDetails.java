/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReceiptDetails {

    /**
     * @return the payment_type
     */
    public String getPayment_type() {
        return payment_type;
    }

    /**
     * @param payment_type the payment_type to set
     */
    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    /**
     * @return the payment_det
     */
    public String getPayment_det() {
        return payment_det;
    }

    /**
     * @param payment_det the payment_det to set
     */
    public void setPayment_det(String payment_det) {
        this.payment_det = payment_det;
    }

    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "RECEIPT_ID")
    private int receiptId;
    private boolean successSave = false;
//    @Basic(optional = false)
//    @Column(name = "DEVOTEE_ID")
    private int devoteeId;
//    @Basic(optional = false)
//    @Column(name = "SERVICE_DATE")
//    @Temporal(TemporalType.DATE)
    private Date serviceDate;
//    @Basic(optional = false)
//    @Column(name = "DONATION")
    private int donation;
//    @Basic(optional = false)
//    @Column(name = "SERVICE")
    private String service;

    private String payment_type;
    private String payment_det;
    
//    @Basic(optional = false)
//    @Column(name = "DATE_CREATED")
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateCreated;
//    @Basic(optional = false)
//    @Column(name = "DATE_MODIFIED")
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateModified;
    private String devoteeName;
    private String devoteePhone;
    private String devoteeEmail;

    ObservableList<ReceiptDetails> rdList = FXCollections.observableArrayList();
    private DevoteeDetails devoteeDet;

    public void setDevoteeName(String devoteeName) {
        this.devoteeName = devoteeName;
    }

    public String getDevoteeName() {
        return devoteeName;
    }

    public void setDevoteeEmail(String devoteeEmail) {
        this.devoteeEmail = devoteeEmail;
    }

    public String getDevoteeEmail() {
        return devoteeEmail;
    }

    public void setDevoteePhone(String devoteePhone) {
        this.devoteePhone = devoteePhone;
    }

    public String getDevoteePhone() {
        return devoteePhone;
    }

    public ReceiptDetails() {
    }

    public ReceiptDetails(int receiptId) {
        this.receiptId = receiptId;
    }

    public void setSuccessSave(boolean successSave) {
        this.successSave = successSave;
    }

    public boolean isSuccessSave() {
        return successSave;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getDevoteeId() {
        return devoteeId;
    }

    public void setDevoteeId(int devoteeId) {
        this.devoteeId = devoteeId;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getDonation() {
        return donation;
    }

    public void setDonation(int donation) {
        this.donation = donation;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public void setReceiptDetails(ReceiptDetails rd) {
        this.rdList.add(rd);
    }

    public ObservableList<ReceiptDetails> getReceiptDetails() {
        return rdList;
    }

    public void setDevoteeDetail(DevoteeDetails dd) {
        this.devoteeDet = dd;
    }


    /*
//    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReceiptDetails)) {
            return false;
        }
        ReceiptDetails other = (ReceiptDetails) object;
        if ((this.receiptId == null && other.receiptId != null) || (this.receiptId != null && !this.receiptId.equals(other.receiptId))) {
            return false;
        }
        return true;
    }*/
    public DevoteeDetails getDevoteeDet() {
        return devoteeDet;
    }
}
