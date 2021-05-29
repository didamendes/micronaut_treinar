package br.com.diogomendes.livros

import br.com.diogomendes.autores.Autor
import br.com.diogomendes.categorias.Categoria
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Livro(
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val preco: BigDecimal,
    val paginas: Int,
    val isbn: String,
    val dataPublicacao: LocalDate,
    @ManyToOne val categoria: Categoria,
    @ManyToOne val autor: Autor
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}