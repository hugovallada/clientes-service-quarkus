package com.github.hugovallada.repository

import com.github.hugovallada.model.Cliente
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClienteRepository : PanacheRepository<Cliente> {

    fun findByName(name: String) = find("name", name).firstResult()

    fun softDelete(id:Long) = update("active = false where id = ?1", id)

}