
package com.crucialtechnologies.task.pogo.customer_record;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PaymentArray {

    @SerializedName("cash")
    @Expose
    private Integer cash;
    @SerializedName("cheque")
    @Expose
    private Integer cheque;
    @SerializedName("credit")
    @Expose
    private Integer credit;
    @SerializedName("billing")
    @Expose
    private Integer billing;

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Integer getCheque() {
        return cheque;
    }

    public void setCheque(Integer cheque) {
        this.cheque = cheque;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getBilling() {
        return billing;
    }

    public void setBilling(Integer billing) {
        this.billing = billing;
    }

}
