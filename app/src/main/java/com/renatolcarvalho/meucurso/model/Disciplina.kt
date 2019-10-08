package com.renatolcarvalho.meucurso.model

// Valores padrões são Strings vazias
data class Disciplina(
    val codigo: String ="",
    var nome: String = "",
    var ementa: String = ""
)