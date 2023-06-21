package com.crucialtechnologies.task.pogo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _2 {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("show_on_top")
    @Expose
    private Integer showOnTop;
    @SerializedName("services")
    @Expose
    private List<Service__1> services;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getShowOnTop() {
        return showOnTop;
    }

    public void setShowOnTop(Integer showOnTop) {
        this.showOnTop = showOnTop;
    }

    public List<Service__1> getServices() {
        return services;
    }

    public void setServices(List<Service__1> services) {
        this.services = services;
    }

}
