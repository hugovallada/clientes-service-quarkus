package com.github.hugovallada.resource

import com.github.hugovallada.model.Cliente
import com.github.hugovallada.repository.ClienteRepository
import org.jboss.resteasy.annotations.Body
import javax.annotation.Resources
import javax.transaction.Transactional
import javax.ws.rs.*

@Path("/clientes")
class ClienteResource(private val repository: ClienteRepository) {

    @POST
    @Transactional
    fun create(cliente: Cliente) : Cliente {
        repository.persist(cliente)
        return cliente
    }

    @GET
    @Path("/all")
    fun listAll() : List<Cliente> = repository.listAll()

    @DELETE
    @Path("{id}")
    @Transactional
    fun delete(@PathParam("id") id: Long) {
        repository.deleteById(id)
    }

    @GET
    @Path("{id}")
    fun getById(@PathParam("id") id: Long) : Cliente {
        repository.findById(id)?.run {
            return this
        } ?: throw IllegalStateException("Illegal Id")
    }

}