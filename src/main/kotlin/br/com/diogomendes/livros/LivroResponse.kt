package br.com.diogomendes.livros

import com.fasterxml.jackson.annotation.JsonFormat

class LivroResponse(livro: Livro) {
    val titulo = livro.titulo
    val resumo = livro.resumo
    val sumario = livro.sumario
    val preco = livro.preco
    val paginas = livro.paginas
    val isbn = livro.isbn
    @JsonFormat(pattern = "yyyy-MM-dd") val dataPulicacao = livro.dataPublicacao
    val categoria = livro.categoria.nome
    val autor = livro.autor.nome
}