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
    private List<Service__2> services;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Service__2> getServices() {
        return services;
    }

    public void setServices(List<Service__2> services) {
        this.services = services;
    }

}
