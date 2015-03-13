package com.strongloop.android.error;

import org.json.JSONObject;

/**
 * The object contains ErrorDetail
 */
public class ErrorInfo {

    private String name;
    private int status;
    private  String message;
    private int statusCode;
    private String code;
    private JSONObject details;

    private ErrorInfo() {}

    public static ErrorInfo parse(JSONObject errorObj) {
        if (null == errorObj) {
            return null;
        }
        ErrorInfo detail = new ErrorInfo();
        detail.name = errorObj.optString("name");
        detail.status = errorObj.optInt("status");
        detail.message = errorObj.optString("message");
        detail.statusCode = errorObj.optInt("statusCode");
        detail.code = errorObj.optString("code");
        detail.details = errorObj.optJSONObject("details");
        return detail;
    }

    public String getName() { return this.name; }
    public int getStatus() { return this.status; }
    public String getMessage() { return this.message; }
    public int getStatusCode() { return this.statusCode; }
    public String getCode() { return this.code; }
    public JSONObject getDetail() { return this.details; }

    @Override
    public String toString() {
        return "ErrorDetail{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", code='" + code + '\'' +
                ", details=" + details +
                '}';
    }
}
