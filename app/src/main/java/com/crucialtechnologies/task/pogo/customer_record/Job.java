
package com.crucialtechnologies.task.pogo.customer_record;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Job {

    @SerializedName("job_id")
    @Expose
    private Integer jobId;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("business_name")
    @Expose
    private String businessName;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_image")
    @Expose
    private String customerImage;
    @SerializedName("job_status")
    @Expose
    private Integer jobStatus;
    @SerializedName("status_title")
    @Expose
    private String statusTitle;
    @SerializedName("requested_date")
    @Expose
    private String requestedDate;
    @SerializedName("request_end_date")
    @Expose
    private String requestEndDate;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("reschedule_date_time")
    @Expose
    private Object rescheduleDateTime;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("services_names")
    @Expose
    private String servicesNames;
    @SerializedName("short_address")
    @Expose
    private String shortAddress;
    @SerializedName("booking_address_by_google")
    @Expose
    private String bookingAddressByGoogle;
    @SerializedName("transaction")
    @Expose
    private Object transaction;
    @SerializedName("close_invoice")
    @Expose
    private String closeInvoice;
    @SerializedName("diagnose")
    @Expose
    private Object diagnose;
    @SerializedName("diagnose_media_count")
    @Expose
    private Integer diagnoseMediaCount;
    @SerializedName("count_proposal")
    @Expose
    private Integer countProposal;
    @SerializedName("count_rtocancel")
    @Expose
    private Integer countRtocancel;
    @SerializedName("total_collected_amount")
    @Expose
    private String totalCollectedAmount;
    @SerializedName("job_sold")
    @Expose
    private Integer jobSold;
    @SerializedName("payment_array")
    @Expose
    private PaymentArray paymentArray;
    @SerializedName("is_confirmed")
    @Expose
    private Integer isConfirmed;
    @SerializedName("is_primary")
    @Expose
    private Integer isPrimary;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getRequestEndDate() {
        return requestEndDate;
    }

    public void setRequestEndDate(String requestEndDate) {
        this.requestEndDate = requestEndDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Object getRescheduleDateTime() {
        return rescheduleDateTime;
    }

    public void setRescheduleDateTime(Object rescheduleDateTime) {
        this.rescheduleDateTime = rescheduleDateTime;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServicesNames() {
        return servicesNames;
    }

    public void setServicesNames(String servicesNames) {
        this.servicesNames = servicesNames;
    }

    public String getShortAddress() {
        return shortAddress;
    }

    public void setShortAddress(String shortAddress) {
        this.shortAddress = shortAddress;
    }

    public String getBookingAddressByGoogle() {
        return bookingAddressByGoogle;
    }

    public void setBookingAddressByGoogle(String bookingAddressByGoogle) {
        this.bookingAddressByGoogle = bookingAddressByGoogle;
    }

    public Object getTransaction() {
        return transaction;
    }

    public void setTransaction(Object transaction) {
        this.transaction = transaction;
    }

    public String getCloseInvoice() {
        return closeInvoice;
    }

    public void setCloseInvoice(String closeInvoice) {
        this.closeInvoice = closeInvoice;
    }

    public Object getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Object diagnose) {
        this.diagnose = diagnose;
    }

    public Integer getDiagnoseMediaCount() {
        return diagnoseMediaCount;
    }

    public void setDiagnoseMediaCount(Integer diagnoseMediaCount) {
        this.diagnoseMediaCount = diagnoseMediaCount;
    }

    public Integer getCountProposal() {
        return countProposal;
    }

    public void setCountProposal(Integer countProposal) {
        this.countProposal = countProposal;
    }

    public Integer getCountRtocancel() {
        return countRtocancel;
    }

    public void setCountRtocancel(Integer countRtocancel) {
        this.countRtocancel = countRtocancel;
    }

    public String getTotalCollectedAmount() {
        return totalCollectedAmount;
    }

    public void setTotalCollectedAmount(String totalCollectedAmount) {
        this.totalCollectedAmount = totalCollectedAmount;
    }

    public Integer getJobSold() {
        return jobSold;
    }

    public void setJobSold(Integer jobSold) {
        this.jobSold = jobSold;
    }

    public PaymentArray getPaymentArray() {
        return paymentArray;
    }

    public void setPaymentArray(PaymentArray paymentArray) {
        this.paymentArray = paymentArray;
    }

    public Integer getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Integer isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Integer getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
    }

}
