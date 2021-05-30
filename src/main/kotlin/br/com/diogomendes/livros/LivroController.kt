package br.com.diogomendes.livros

import br.com.diogomendes.autores.AutorRepository
import br.com.diogomendes.categorias.CategoriaRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
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
        val uri = UriBuilder.of("/livros/{id}").expand(mutableMapOf(Pair("id", livro.id)))

        return HttpResponse.created(LivroResponse(livro), uri)
    }

    @Put(uri = "/{id}")
    @Transactional
    fun alterar(
        @PathVariable id: Long,
        @Body @Valid alterarLivroRequest: AlterarLivroRequest
    ): HttpResponse<Any> {
        val livroOptional = livroRepository.findById(id)

        if (livroOptional.isEmpty) {
            return HttpResponse.notFound()
        }

        livroOptional.get().apply {
            titulo = alterarLivroRequest.titulo
            resumo = alterarLivroRequest.resumo
            sumario = alterarLivroRequest.sumario
            preco = alterarLivroRequest.preco
            paginas = alterarLivroRequest.paginas
            isbn = alterarLivroRequest.isbn
            dataPublicacao = alterarLivroRequest.dataPublicacao
        }

        return HttpResponse.ok(LivroResponse(livroOptional.get()))
    }

    @Delete(uri = "/{id}")
    @Transactional
    fun remover(@PathVariable id: Long): HttpResponse<Any> {
        val livroOptional = livroRepository.findById(id)

        if (livroOptional.isEmpty) {
            return HttpResponse.notFound()
        }

        livroRepository.delete(livroOptional.get())

        return HttpResponse.noContent()
    }

}