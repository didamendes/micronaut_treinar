package br.com.diogomendes.categorias

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class CategoriaRequest(@field:NotBlank val nome: String) {
    fun paraCategoria(): Categoria {
        return Categoria(nome)
    }
}