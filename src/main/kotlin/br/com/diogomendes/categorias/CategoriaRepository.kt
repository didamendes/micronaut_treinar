package br.com.diogomendes.categorias

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CategoriaRepository: JpaRepository<Categoria, Long>