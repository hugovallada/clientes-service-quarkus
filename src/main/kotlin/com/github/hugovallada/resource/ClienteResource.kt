package com.github.hugovallada.resource

import com.github.hugovallada.dto.ClienteRequest
import com.github.hugovallada.model.Cliente
import com.github.hugovallada.repository.ClienteRepository
import com.github.hugovallada.service.ClienteService
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.jboss.resteasy.annotations.Body
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/clientes")
@Produces(value = [MediaType.APPLICATION_JSON])
class ClienteResource(private val repository: ClienteRepository, private val service: ClienteService) {

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
    fun delete(@PathParam("id") id: Long) : Response {
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
    fun softDelete(@PathParam("id") id:Long) : Response {
        service.softDelete(id)
        return Response.noContent().build()
    }

    @GET
    @Path("/name")
    fun getByName(@QueryParam("name") name: String) : Response {
        return Response.ok().entity(service.getByName(name)).build()
    }

}