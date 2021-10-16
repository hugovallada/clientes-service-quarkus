package com.github.hugovallada.model

import org.hibernate.annotations.Where
import javax.persistence.*
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
        var active: Boolean? = null
) {
        @PrePersist
        fun active(){
                this.active = true
        }
}