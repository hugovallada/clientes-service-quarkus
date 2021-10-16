package com.github.hugovallada.translator

import com.github.hugovallada.dto.ClienteRequest
import com.github.hugovallada.dto.ClienteResponse
import com.github.hugovallada.model.Cliente

class ClienteTranslator {

    companion object {

        fun toResponse(cliente: Cliente) = ClienteResponse(
            id = cliente.id ?: throw IllegalStateException("Should have an id"),
            name = cliente.name,
            documento = cliente.documento,
            endereco = cliente.endereco
        )

        fun of(request: ClienteRequest) = Cliente(
            name = request.name,
            endereco = request.endereco,
            documento = request.documento
        )

    }

}