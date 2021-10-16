package com.github.hugovallada.dto

import javax.json.bind.annotation.JsonbCreator
import javax.validation.constraints.NotBlank


data class ClienteRequest @JsonbCreator constructor(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val documento: String,
    @field:NotBlank
    val endereco: String
)