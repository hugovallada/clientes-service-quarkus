package com.github.hugovallada.resource

import com.github.hugovallada.model.Cliente
import com.github.hugovallada.repository.ClienteRepository
import org.jboss.resteasy.annotations.ResponseObject
import org.jboss.resteasy.annotations.Status
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/clientes")
@Produces(value = [MediaType.APPLICATION_JSON])
class ClienteResource(private val repository: ClienteRepository) {

    @POST
    @Transactional
    fun create(cliente: Cliente): Cliente {
        repository.persist(cliente)
        return cliente
    }

    @GET
    @Path("/all")
    fun listAll(): List<Cliente> = repository.listAll()

    @DELETE
    @Path("{id}")
    @Transactional
    fun delete(@PathParam("id") id: Long) {
        repository.deleteById(id)
    }

    @GET
    @Path("{id}")
    fun getById(@PathParam("id") id: Long): Cliente {
        repository.findById(id)?.run {
            return this
        } ?: throw IllegalStateException("Illegal Id")
    }

    @PUT
    @Path("/soft/{id}")
    @Transactional
    fun softDelete(@PathParam("id") id:Long) {
        repository.softDelete(id)
    }

    @GET
    @Path("/name")
    fun getByName(@QueryParam("name") name: String) : Cliente {
        return repository.findByName(name)?.run {
            return this
        } ?: throw IllegalStateException("Illegal name")
    }

}