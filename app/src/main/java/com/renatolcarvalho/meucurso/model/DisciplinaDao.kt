package com.renatolcarvalho.meucurso.model

interface DisciplinaDao {
    fun createDisciplina(disciplina: Disciplina)
    fun readDisciplina(codigo: String): Disciplina
    fun readDisciplinas(): MutableList<Disciplina>
    fun updateDisciplina(disciplina: Disciplina)
    fun deleteDisciplina(codigo: String)
}