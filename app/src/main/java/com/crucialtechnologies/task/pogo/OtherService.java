package com.crucialtechnologies.task.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherService {

    @SerializedName("service_id")
    @Expose
    private Integer serviceId;
    @SerializedName("service_section_id")
    @Expose
    private String serviceSectionId;
    @SerializedName("service_keyword")
    @Expose
    private Object serviceKeyword;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("service_amount")
    @Expose
    private String serviceAmount;
    @SerializedName("service_image")
    @Expose
    private String serviceImage;
    @SerializedName("service_image_hexcode")
    @Expose
    private String serviceImageHexcode;
    @SerializedName("service_level")
    @Expose
    private Integer serviceLevel;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("most_popular_item")
    @Expose
    private Integer mostPopularItem;
    @SerializedName("most_popular_item_priority")
    @Expose
    private Integer mostPopularItemPriority;
    @SerializedName("category_or_item")
    @Expose
    private Integer categoryOrItem;
    @SerializedName("show_customer_only")
    @Expose
    private Integer showCustomerOnly;
    @SerializedName("is_maintenance")
    @Expose
    private Integer isMaintenance;
    @SerializedName("is_inspection")
    @Expose
    private Integer isInspection;
    @SerializedName("added_by")
    @Expose
    private Integer addedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;
    @SerializedName("next_elements")
    @Expose
    private Integer nextElements;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceSectionId() {
        return serviceSectionId;
    }

    public void setServiceSectionId(String serviceSectionId) {
        this.serviceSectionId = serviceSectionId;
    }

    public Object getServiceKeyword() {
        return serviceKeyword;
    }

    public void setServiceKeyword(Object serviceKeyword) {
        this.serviceKeyword = serviceKeyword;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
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

    public Integer getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(Integer serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getMostPopularItem() {
        return mostPopularItem;
    }

    public void setMostPopularItem(Integer mostPopularItem) {
        this.mostPopularItem = mostPopularItem;
    }

    public Integer getMostPopularItemPriority() {
        return mostPopularItemPriority;
    }

    public void setMostPopularItemPriority(Integer mostPopularItemPriority) {
        this.mostPopularItemPriority = mostPopularItemPriority;
    }

    public Integer getCategoryOrItem() {
        return categoryOrItem;
    }

    public void setCategoryOrItem(Integer categoryOrItem) {
        this.categoryOrItem = categoryOrItem;
    }

    public Integer getShowCustomerOnly() {
        return showCustomerOnly;
    }

    public void setShowCustomerOnly(Integer showCustomerOnly) {
        this.showCustomerOnly = showCustomerOnly;
    }

    public Integer getIsMaintenance() {
        return isMaintenance;
    }

    public void setIsMaintenance(Integer isMaintenance) {
        this.isMaintenance = isMaintenance;
    }

    public Integer getIsInspection() {
        return isInspection;
    }

    public void setIsInspection(Integer isInspection) {
        this.isInspection = isInspection;
    }

    public Integer getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getNextElements() {
        return nextElements;
    }

    public void setNextElements(Integer nextElements) {
        this.nextElements = nextElements;
    }

}
