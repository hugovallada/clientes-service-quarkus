package com.github.hugovallada.dto

data class ClienteResponse constructor(
    val id: Long,
    val name: String,
    val documento: String,
    val endereco: String
)
