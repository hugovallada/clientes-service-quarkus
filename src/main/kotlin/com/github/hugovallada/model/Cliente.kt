package com.github.hugovallada.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Where
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
@Where(clause = "active = true")
class Cliente(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:NotBlank
        var name: String,
        @field:NotBlank
        var endereco: String,
        @field:NotBlank
        var documento: String,
        var active: Boolean? = true,
        @CreationTimestamp
        var creationDate: LocalDateTime? = null
)