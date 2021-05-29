package br.com.diogomendes.categorias

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/categorias")
class CategoriaController(val categoriaRepository: CategoriaRepository) {

    @Post
    fun salvar(@Body @Valid categoriaRequest: CategoriaRequest) {
        val categoria: Categoria = categoriaRequest.paraCategoria()

        categoriaRepository.save(categoria)
        println("Categoria criado com sucesso")
    }

}