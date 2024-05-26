package com.escooter.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * Utility class for building JSON responses.
 */
public class JsonResponseBuilder {
    private static final Logger logger = LoggerFactory.getLogger(JsonResponseBuilder.class);

    public static String buildSuccessResponse(String message){
        return buildResponse(true, message, null);
    }

    public static String buildSuccessResponse(String message , String data){
        return buildResponse(true, message , data);
    }

    public static String buildSuccessResponse(String message , boolean data){
        return buildResponse(true, message , data);
    }

    public static String buildSuccessResponse(String message , JSONArray data){
        return buildResponse(true, message , data);
    }

    public static String buildSuccessResponse(String message , JSONObject data){
        return buildResponse(true, message , data);
    }
    
    public static String buildErrorResponse(String message){
        return buildResponse(false, message , null);
    }

    /**
     * Builds a JSON response.
     *
     * @param status  The status of the response (true for success, false for error).
     * @param message The message of the response.
     * @param data    The data of the response.
     * @return The JSON response as a string.
     */
    private static String buildResponse(boolean status, String message, Object data) {
        JSONObject jsonResponse = new JSONObject();
        try {
            jsonResponse.put("status", status);
            jsonResponse.put("message", message);
            if(data != null){
                jsonResponse.put("data", data);
            }
        } catch (JSONException e) {
            logger.error("JSON creation error", e);
        }
        return jsonResponse.toString();
    }
}
