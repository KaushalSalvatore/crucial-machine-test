package com.crucialtechnologies.task.service;

import com.crucialtechnologies.task.pogo.Success;
import com.crucialtechnologies.task.pogo.customer_record.CustomerRecords;

import com.crucialtechnologies.task.pogo.sub_category.Subcategory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("other?func=section_with_services&latitude=22.724470&longitude=75.905115&from=home")
    Call<Success> getcategories();

    @POST("other?func=service")
    Call<Subcategory> getSubcategory(@Query("service_id") String source);


    @GET("technician?func=project_history")
    Call<CustomerRecords> getJobList(@Query("account_id") int perpagecount,
                                        @Query("page") int pagenum);



}
