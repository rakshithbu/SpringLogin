package org.pkb.springlogin.model;

/**
 * Created by Rakshith on 9/1/17.
 */
public class ValidationResponse {
    private String status;
    private String message;
    private Object successObject;
    private Object errorObject;
    private String redirectUrl;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getSuccessObject() {
        return successObject;
    }

    public void setSuccessObject(Object successObject) {
        this.successObject = successObject;
    }

    public Object getErrorObject() {
        return errorObject;
    }

    public void setErrorObject(Object errorObject) {
        this.errorObject = errorObject;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "ValidationResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", successObject=" + successObject +
                ", errorObject=" + errorObject +
                ", redirectUrl='" + redirectUrl + '\'' +
                '}';
    }
}
