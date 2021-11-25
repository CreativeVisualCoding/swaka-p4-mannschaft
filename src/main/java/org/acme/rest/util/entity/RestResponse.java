package org.acme.rest.util.entity;

import java.util.Map;

public class RestResponse {
    public String jsonapi;
    public Map<String, String> meta;
    public Error[] errors;
    public BaseEntity[] data;
    public BaseEntity[] included;
    public PaginationLink link;

    public static RestResponse getDataResponseFromArray(BaseEntity[] data) {
        RestResponse restResponse = new RestResponse();
        for (BaseEntity entity : data) {
            entity.links.self = "";
        }
        restResponse.data = data;
        return restResponse;
    }

    public static RestResponse getDataResponse(BaseEntity data) {
        return getDataResponseFromArray(new BaseEntity[]{data});
    }

    public static RestResponse getErrorResponse(Error error) {
        RestResponse restResponse = new RestResponse();
        restResponse.errors = new Error[]{error};
        return restResponse;
    }

    public static RestResponse getErrorResponseFromArray(Error[] errors) {
        RestResponse restResponse = new RestResponse();
        restResponse.errors = errors;
        return restResponse;
    }
}
