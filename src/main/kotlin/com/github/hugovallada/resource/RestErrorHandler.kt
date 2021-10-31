package com.github.hugovallada.resource

import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerExceptionMapper
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Produces(MediaType.APPLICATION_JSON)
class RestErrorHandler {

    @ServerExceptionMapper
    fun illegalRequestToBadRequest(ex: IllegalStateException): RestResponse<ApiError> {
        return RestResponse.status(Response.Status.BAD_REQUEST, ApiError(ex.message!!, 400))
    }

    @ServerExceptionMapper
    fun generalError(ex: Exception) : RestResponse<ApiError> {
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, ApiError(ex.message!!, 500))
    }
}

data class ApiError(val msg: String, val errorCode: Int)