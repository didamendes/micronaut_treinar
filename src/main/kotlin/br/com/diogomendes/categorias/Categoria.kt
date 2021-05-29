package br.com.diogomendes.categorias

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Categoria(val nome: String) {
    @Id
    @GeneratedValue
    var id: Long? = null
}