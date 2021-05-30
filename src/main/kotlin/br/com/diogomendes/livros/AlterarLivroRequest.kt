package br.com.diogomendes.livros

import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class AlterarLivroRequest(
    @field:NotBlank val titulo: String,
    @field:NotBlank val resumo: String,
    @field:NotBlank val sumario: String,
    @field:NotNull val preco: BigDecimal,
    @field:NotNull val paginas: Int,
    @field:NotBlank val isbn: String,
    @field:FutureOrPresent @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") val dataPublicacao: LocalDate,
)