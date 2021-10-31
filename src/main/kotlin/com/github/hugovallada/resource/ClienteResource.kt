package com.github.hugovallada.resource

import com.github.hugovallada.dto.ClienteRequest
import com.github.hugovallada.dto.ClienteResponse
import com.github.hugovallada.service.ClienteService
import org.jboss.resteasy.reactive.RestResponse
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/clientes")
@Produces(value = [MediaType.APPLICATION_JSON])
class ClienteResource(private val service: ClienteService) {

    @POST
    fun create(@Valid request: ClienteRequest): Response {
        val cliente = service.create(request)
        return Response.status(201).entity(cliente).build()
    }

    @GET
    @Path("/all")
    fun listAll(): Response = Response.ok().entity(service.getAll()).build()

    @DELETE
    @Path("{id}")
    fun delete(@PathParam("id") id: Long): Response {
        service.delete(id)
        return Response.noContent().build()
    }

    @GET
    @Path("{id}")
    fun getById(@PathParam("id") id: Long): Response {
        return Response.ok().entity(service.getById(id)).build()
    }

    @PUT
    @Path("/soft/{id}")
    fun softDelete(@PathParam("id") id: Long): Response {
        service.softDelete(id)
        return Response.noContent().build()
    }

    @GET
    @Path("/name")
    fun getByName(@QueryParam("name") name: String): Response {
        return Response.ok().entity(service.getByName(name)).build()
    }

    @GET
    @Path("/response")
    fun getBySpec(@QueryParam("name") name: String) : RestResponse<ClienteResponse> {
        return RestResponse.ok(service.getBySpec(name))
    }

}