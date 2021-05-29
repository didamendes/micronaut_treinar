package br.com.diogomendes.categorias

import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/categorias")
class CategoriaController(val categoriaRepository: CategoriaRepository) {

    @Get
    fun listar():HttpResponse<List<CategoriaResponse>> {
        val categorias = categoriaRepository.findAll()

        val categoriasResponse = categorias.map { categoria -> CategoriaResponse(categoria) }

        return HttpResponse.ok(categoriasResponse)
    }

    @Post
    fun salvar(@Body @Valid categoriaRequest: CategoriaRequest): HttpResponse<CategoriaResponse> {
        val categoria: Categoria = categoriaRequest.paraCategoria()

        categoriaRepository.save(categoria)
        val uri = UriBuilder.of("/categorias/{id}").expand(mutableMapOf(Pair("id", categoria.id)))

        return HttpResponse.created(CategoriaResponse(categoria), uri)
    }

    @Put(uri = "/{id}")
    @Transactional
    fun alterar(@PathVariable id:Long, @Body @Valid categoriaRequest: CategoriaRequest): HttpResponse<Any> {
        val categoriaOptional = categoriaRepository.findById(id)

        if (categoriaOptional.isEmpty) {
            return HttpResponse.notFound()
        }

        categoriaOptional.get().apply { nome = categoriaRequest.nome }
        return HttpResponse.ok(CategoriaResponse(categoriaOptional.get()))
    }

    @Delete(uri = "/{id}")
    @Transactional
    fun remover(@PathVariable id:Long): HttpResponse<Any> {
        val categoriaOptional = categoriaRepository.findById(id)

        if (categoriaOptional.isEmpty) {
            return HttpResponse.notFound()
        }

        categoriaRepository.delete(categoriaOptional.get())
        return HttpResponse.noContent()
    }

}