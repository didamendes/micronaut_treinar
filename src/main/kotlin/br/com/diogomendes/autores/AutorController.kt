package br.com.diogomendes.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(val autorRepository: AutorRepository) {

    @Post
    fun salvar(@Body @Valid autorRequest: AutorRequest): HttpResponse<AutorResponse> {
        val autor = autorRequest.paraAutor()
        autorRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(AutorResponse(autor), uri)
    }

}