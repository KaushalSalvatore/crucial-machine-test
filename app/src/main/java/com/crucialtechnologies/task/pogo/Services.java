package com.crucialtechnologies.task.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Services {

    @SerializedName("1")
    @Expose
    private _1 _1;
    @SerializedName("2")
    @Expose
    private _2 _2;
    @SerializedName("other")
    @Expose
    private Other other;

    public _1 get_1() {
        return _1;
    }

    public void set_1(_1 _1) {
        this._1 = _1;
    }

    public _2 get_2() {
        return _2;
    }

    public void set_2(_2 _2) {
        this._2 = _2;
    }

    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }

}
