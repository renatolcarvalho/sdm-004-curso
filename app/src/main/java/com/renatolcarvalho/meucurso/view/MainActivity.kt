package com.renatolcarvalho.meucurso.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.renatolcarvalho.meucurso.R
import com.renatolcarvalho.meucurso.adapter.DisciplinasAdapter
import com.renatolcarvalho.meucurso.controller.CursoController
import com.renatolcarvalho.meucurso.model.Disciplina
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    lateinit var listaDisciplinas: MutableList<Disciplina>
    lateinit var disciplinasAdapter: DisciplinasAdapter
    lateinit var cursoController: CursoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // lista de disciplinas do Adapter
        listaDisciplinas = mutableListOf()
        disciplinasAdapter = DisciplinasAdapter(this,
            R.layout.disciplina_item,
            listaDisciplinas)

        // Setando o adapter do ListView
        disciplinasLv.adapter = disciplinasAdapter
        // Criando Controller e solicitando atualização de lista
        cursoController = CursoController(this)
        // Inserindo disciplinas falsas
        insereDisciplinasFalsas()

        // Solicitando todas as disciplinas para o Controller
        cursoController.buscaTodas()

        // Registrando o ListView para conter o menu de contexto
        registerForContextMenu(disciplinasLv)

        // Setando listener de clique nos itens
        disciplinasLv.setOnItemClickListener(this)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?)
    {
        menuInflater.inflate(R.menu.menu_contexto, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        // Traz informações sobre o item que foi clicado
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when(item.itemId) {
            R.id.deletarMenuItem -> {
                // Chama Controller para remover disciplina
                cursoController.remove(listaDisciplinas[info.position].codigo)
                Toast.makeText(this, "${listaDisciplinas[info.position].nome}",
                    Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onItemClick(parent: AdapterView<*>?,
                             view: View?, position: Int, id: Long) {
        val disciplina = listaDisciplinas[position]
        // Criando um AlertDialog para mostrar a ementa da disciplina
        val ementaDialog = with(AlertDialog.Builder(this)) {
            setTitle(disciplina.codigo)
            setMessage("${disciplina.nome} :\n ${disciplina.ementa}")
            create()
        }

        // Mostrando AlertDialog
        ementaDialog.show()
    }

    private fun insereDisciplinasFalsas() {
        for (i in 1..50) {
            val d = Disciplina("D${i}", "Disciplina ${i}", "Ementa ${i}")
            cursoController.insereDisciplina(d)
        }
    }

    fun atualizaLista(listaAtualizada: List<Disciplina>) {
        // Limpa lista anterior (abordagem de teste)
        listaDisciplinas.clear()
        // Adiciona os elementos retornados pelo Controller
        listaDisciplinas.addAll(listaAtualizada)
        // Notifica o Adapter das mudanças na sua fonte de daddos
        disciplinasAdapter.notifyDataSetChanged()
    }
}