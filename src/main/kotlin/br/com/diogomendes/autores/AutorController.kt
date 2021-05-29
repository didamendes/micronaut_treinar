package br.com.diogomendes.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid
import kotlin.reflect.KClass

@Validated
@Controller("/autores")
class AutorController(val autorRepository: AutorRepository) {

    @Get
    fun listar(): HttpResponse<List<AutorResponse>>? {
        val autores = autorRepository.findAll()
        val autoresResponse = autores.map { autor -> AutorResponse(autor) }
        return HttpResponse.ok(autoresResponse)
    }

    @Post
    @Transactional
    fun salvar(@Body @Valid autorRequest: AutorRequest): HttpResponse<AutorResponse> {
        val autor = autorRequest.paraAutor()
        autorRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(AutorResponse(autor), uri)
    }

    @Put(uri = "/{id}")
    @Transactional
    fun alterar(@PathVariable id: Long, @Body @Valid autorRequest: AutorRequest): HttpResponse<Any> {
        val autorOptional = autorRepository.findById(id)

        if (autorOptional.isEmpty) {
            return HttpResponse.notFound()
        }

        autorOptional.get().apply {
            nome = autorRequest.nome
            email = autorRequest.email
            descricao = autorRequest.descricao
        }

        return HttpResponse.ok(AutorResponse(autorOptional.get()))
    }

    @Delete(uri = "/{id}")
    @Transactional
    fun remover(@PathVariable id: Long): HttpResponse<Any> {
        val autorOptional = autorRepository.findById(id)

        if (autorOptional.isEmpty) {
            return HttpResponse.notFound()
        }

        autorRepository.delete(autorOptional.get())
        return HttpResponse.noContent()
    }

}