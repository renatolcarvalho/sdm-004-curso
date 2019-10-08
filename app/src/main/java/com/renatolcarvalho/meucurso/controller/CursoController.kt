package com.renatolcarvalho.meucurso.controller

import com.renatolcarvalho.meucurso.view.MainActivity
import com.renatolcarvalho.meucurso.model.Disciplina
import com.renatolcarvalho.meucurso.model.DisciplinaDao
import com.renatolcarvalho.meucurso.model.DisciplinaSqlite

class CursoController(val mainActivity: MainActivity) {
    val disciplinaDao: DisciplinaDao
    init {
        disciplinaDao = DisciplinaSqlite(mainActivity)
    }
    fun insereDisciplina(disciplina: Disciplina){
        disciplinaDao.createDisciplina(disciplina)
        buscaTodas()
    }
    fun buscaDisciplina(codigo: String){
        val disciplina: Disciplina = disciplinaDao.readDisciplina(codigo)
        mainActivity.atualizaLista(mutableListOf(disciplina))
    }
    fun buscaTodas() {
        mainActivity.atualizaLista(disciplinaDao.readDisciplinas())
    }
    fun atualiza(disciplina: Disciplina) {
        disciplinaDao.updateDisciplina(disciplina)
        buscaTodas()
    }
    fun remove(codigo: String) {
        disciplinaDao.deleteDisciplina(codigo)
        buscaTodas()
    }
}