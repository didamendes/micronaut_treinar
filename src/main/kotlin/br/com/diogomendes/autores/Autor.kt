package br.com.diogomendes.autores

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Autor(
    var nome: String,
    var email: String,
    var descricao: String
) {
    @Id
    @GeneratedValue
    var id: Long? = null

    val criadaEm: LocalDateTime = LocalDateTime.now()
}