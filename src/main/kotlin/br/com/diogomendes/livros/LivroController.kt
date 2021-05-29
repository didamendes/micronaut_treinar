package br.com.diogomendes.livros

import br.com.diogomendes.autores.AutorRepository
import br.com.diogomendes.categorias.CategoriaRepository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/livros")
class LivroController(
    val livroRepository: LivroRepository,
    val autorRepository: AutorRepository,
    val categoriaRepository: CategoriaRepository
) {

    @Post
    fun salvar(@Body @Valid livroRequest: LivroRequest) {
        val autor = autorRepository.findById(livroRequest.idAutor)
        val categoria = categoriaRepository.findById(livroRequest.idCategoria)

        val livro: Livro = livroRequest.paraLivro(autor.get(), categoria.get())
        livroRepository.save(livro)
    }

}