package br.com.diogomendes.livros

import br.com.diogomendes.autores.AutorRepository
import br.com.diogomendes.categorias.CategoriaRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/livros")
class LivroController(
    val livroRepository: LivroRepository,
    val autorRepository: AutorRepository,
    val categoriaRepository: CategoriaRepository
) {

    @Get
    fun listar(): HttpResponse<List<LivroResponse>> {
        val livros = livroRepository.findAll()

        val livrosResponse = livros.map { livro -> LivroResponse(livro) }

        return HttpResponse.ok(livrosResponse)
    }

    @Post
    fun salvar(@Body @Valid livroRequest: LivroRequest): HttpResponse<Any> {
        val autor = autorRepository.findById(livroRequest.idAutor)
        val categoria = categoriaRepository.findById(livroRequest.idCategoria)

        if (autor.isEmpty || categoria.isEmpty) {
            return HttpResponse.notFound()
        }

        val livro: Livro = livroRequest.paraLivro(autor.get(), categoria.get())
        livroRepository.save(livro)
        UriBuilder.of("/livros/{id}").expand(mutableMapOf(Pair("id", livro.id)))

        return HttpResponse.created(LivroResponse(livro), )
    }

}