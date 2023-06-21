package com.crucialtechnologies.task.pogo.sub_category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceFee {
    @SerializedName("service_id")
    @Expose
    private Integer serviceId;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("service_image")
    @Expose
    private String serviceImage;
    @SerializedName("service_image_hexcode")
    @Expose
    private String serviceImageHexcode;
    @SerializedName("service_amount")
    @Expose
    private String serviceAmount;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getServiceImageHexcode() {
        return serviceImageHexcode;
    }

    public void setServiceImageHexcode(String serviceImageHexcode) {
        this.serviceImageHexcode = serviceImageHexcode;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }
}
