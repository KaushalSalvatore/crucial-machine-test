package com.crucialtechnologies.task.pogo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Other {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("services")
    @Expose
    private List<Service> otherServices;

    public List<Service> getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(List<Service> otherServices) {
        this.otherServices = otherServices;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
