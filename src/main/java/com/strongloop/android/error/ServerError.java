package com.strongloop.android.error;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * The object represents HTTP Error raised from server (statusCode != 200)
 */
public class ServerError extends Error {

    private int statusCode;
    private byte[] responseBody;
    private org.apache.http.Header[] header;
    private String charset;

    public ServerError(Throwable error) {
        super(error);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public byte[] getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(byte[] responseBody, String charset) {
        this.responseBody = responseBody;
        this.charset = charset;
    }

    public String getResponseBodyAsString() throws UnsupportedEncodingException {
        return new String(responseBody, charset);
    }

    public JSONObject getResponseBodyAsJSON() throws UnsupportedEncodingException, JSONException {
        return new JSONObject(getResponseBodyAsString());
    }

    public ErrorDetail getDetail() throws UnsupportedEncodingException, JSONException {
        JSONObject errorObject = getResponseBodyAsJSON();
        return ErrorDetail.parse(errorObject.optJSONObject("error"));
    }

    public org.apache.http.Header[] getHeader() {
        return header;
    }

    public void setHeader(org.apache.http.Header[] header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "ServerError{" +
                "statusCode=" + statusCode +
                ", responseBody=" + Arrays.toString(responseBody) +
                ", header=" + Arrays.toString(header) +
                ", charset='" + charset + '\'' +
                '}';
    }
}
