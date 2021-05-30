package br.com.diogomendes.autores

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals as assertEquals1

@MicronautTest
internal class AutorControllerTest {

    @field:Inject
    @field:Client("/")
    lateinit var httpClient: HttpClient

//    @field:Inject
//    lateinit var autorRepository: AutorRepository

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
    }

    @AfterEach
    internal fun tearDown() {
//        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve salvar um autor`() {
 //       val autorRequest = AutorRequest(nome = "Diogo Mendes", email = "diogo@zup.com.br", descricao = "Teste")

//        val request = HttpRequest.POST("/autores", autorRequest)
//        val response: HttpResponse<Any> = httpClient.toBlocking().exchange(request, Any::class.java)
//
//        Assertions.assertEquals(HttpStatus.CREATED, response.status)

    }
}