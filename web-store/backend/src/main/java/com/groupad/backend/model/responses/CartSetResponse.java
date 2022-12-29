package com.groupad.backend.model.responses;

import java.util.ArrayList;
import java.util.List;

public class CartSetResponse {
    private Boolean response;
    private List<String> errMsgs;

    public CartSetResponse() {
        response = true;
        errMsgs = new ArrayList<>();
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public List<String> getErrMsgs() {
        return errMsgs;
    }

    public void setErrMsgs(List<String> errMsgs) {
        this.errMsgs = errMsgs;
    }

    public void addErrMsg(String errMsg) {
        errMsgs.add(errMsg);
    }

}
