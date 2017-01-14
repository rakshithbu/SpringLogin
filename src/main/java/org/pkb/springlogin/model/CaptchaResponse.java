package org.pkb.springlogin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Rakshith on 9/1/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponse {
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "CaptchaResponse{" +
                "success='" + success + '\'' +
                '}';
    }
}
