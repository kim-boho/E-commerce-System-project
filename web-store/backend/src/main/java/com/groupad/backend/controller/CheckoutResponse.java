package com.groupad.backend.controller;

import java.util.ArrayList;
import java.util.List;

public class CheckoutResponse {
    private Boolean result = true;
    private List<String> errMsgs = new ArrayList<>();

    public void addErrMsg(String errMsg) {
        errMsgs.add(errMsg);
    }

    public void addErrMsgs(List<String> errMsgs) {
        this.errMsgs.addAll(errMsgs);
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

}
