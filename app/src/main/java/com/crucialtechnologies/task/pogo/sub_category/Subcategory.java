package com.crucialtechnologies.task.pogo.sub_category;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Subcategory {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("services")
    @Expose
    private List<Service> services;
//    @SerializedName("next_elements")
//    @Expose
//    private NextElements nextElements;
//    @SerializedName("service_fee")
//    @Expose
//    private ServiceFee serviceFee;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

//    public NextElements getNextElements() {
//        return nextElements;
//    }
//
//    public void setNextElements(NextElements nextElements) {
//        this.nextElements = nextElements;
//    }
//
//    public ServiceFee getServiceFee() {
//        return serviceFee;
//    }
//
//    public void setServiceFee(ServiceFee serviceFee) {
//        this.serviceFee = serviceFee;
//    }

}
