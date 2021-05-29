package br.com.diogomendes.autores

import com.fasterxml.jackson.annotation.JsonFormat

class AutorResponse(autor: Autor) {
    val id = autor.id
    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
    @JsonFormat(pattern = "yyyy-MM-dd") val criadaEm = autor.criadaEm
}
