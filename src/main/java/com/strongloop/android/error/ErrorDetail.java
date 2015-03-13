package com.strongloop.android.error;

import org.json.JSONObject;

/**
 * The object contains ErrorDetail
 */
public class ErrorDetail {

    private String name;
    private int status;
    private  String message;
    private int statusCode;
    private String code;

    private ErrorDetail() {}

    public static ErrorDetail parse(JSONObject errorObj) {
        if (null == errorObj) {
            return null;
        }
        ErrorDetail detail = new ErrorDetail();
        detail.name = errorObj.optString("name");
        detail.status = errorObj.optInt("status");
        detail.message = errorObj.optString("message");
        detail.statusCode = errorObj.optInt("statusCode");
        detail.code = errorObj.optString("code");
        return detail;
    }

    public String getName() { return this.name; }
    public int getStatus() { return this.status; }
    public String getMessage() { return this.message; }
    public int getStatusCode() { return this.statusCode; }
    public String getCode() { return this.code; }

    @Override
    public String toString() {
        return "ErrorDetail{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", code='" + code + '\'' +
                '}';
    }
}
