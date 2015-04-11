package com.strongloop.android.error;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * The object represents HTTP Error raised from server (statusCode != 200)
 */
public class ServerError extends Error {

    private static final String TAG = ServerError.class.getSimpleName();

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

    public ErrorInfo getResponseBodyAsInfo() throws UnsupportedEncodingException, JSONException {
        JSONObject errorObject = getResponseBodyAsJSON();
        return ErrorInfo.parse(errorObject.optJSONObject("error"));
    }

    public org.apache.http.Header[] getHeader() {
        return header;
    }

    public void setHeader(org.apache.http.Header[] header) {
        this.header = header;
    }

    @Override
    public String toString() {
        String body = null;
        try {
            body = getResponseBodyAsString();
        } catch (Exception e) {
            Log.e(TAG, "Failed to stringify body", e);
        }
        if (null == body) {
            body = Arrays.toString(responseBody);
        }
        return "ServerError{" +
                "statusCode=" + statusCode +
                ", responseBody=" + body +
                ", header=" + Arrays.toString(header) +
                ", charset='" + charset + '\'' +
                '}';
    }
}
