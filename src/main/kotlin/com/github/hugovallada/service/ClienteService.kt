package com.github.hugovallada.service

import com.github.hugovallada.dto.ClienteRequest
import com.github.hugovallada.dto.ClienteResponse
import com.github.hugovallada.model.Cliente
import com.github.hugovallada.repository.ClienteRepository
import com.github.hugovallada.translator.ClienteTranslator
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class ClienteService(private val repository: ClienteRepository, val logger: Logger) {

    @Transactional
    fun create(request: ClienteRequest) : ClienteResponse {
        val cliente = ClienteTranslator.of(request)
        repository.persist(cliente)
        return ClienteTranslator.toResponse(cliente)
    }

    fun getAll() : List<ClienteResponse> {
        val clientes = repository.listAll()
        return clientes.map { cliente -> ClienteTranslator.toResponse(cliente) }
    }

    fun getById(id: Long) : ClienteResponse {
       val cliente = findById(id)
       return ClienteTranslator.toResponse(cliente)
    }

    @Transactional
    fun delete(id : Long) {
        val cliente = findById(id)
        repository.delete(cliente)
    }

    fun getByName(name: String) : ClienteResponse {
        val cliente = repository.findByName(name) ?: throw IllegalStateException("Not Found")
        return ClienteTranslator.toResponse(cliente)
    }

    @Transactional
    fun softDelete(id: Long) {
        repository.softDelete(id)
    }

    private fun findById(id: Long) : Cliente {
        return repository.findById(id) ?: throw IllegalStateException("Not Found")
    }

}