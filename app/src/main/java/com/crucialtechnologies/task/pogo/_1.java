package com.crucialtechnologies.task.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class _1 {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("show_on_top")
    @Expose
    private Integer showOnTop;
    @SerializedName("services")
    @Expose
    private List<Service> service;

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

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

}
