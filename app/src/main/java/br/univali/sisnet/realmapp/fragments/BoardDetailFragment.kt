package br.univali.sisnet.realmapp.fragments

import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.TextView
import br.univali.sisnet.realmapp.R
import br.univali.sisnet.realmapp.domain.Todo
import br.univali.sisnet.realmapp.view.adapters.TodoAdapter

class BoardDetailFragment : Fragment() {

    var rvTodos: RecyclerView? = null
    var tvBoardTitle: TextView? = null
    var tvBoardDate: TextView? = null
    var btAddTodo: Button? = null
    var adapter: TodoAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_board_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btAddTodo = view!!.findViewById(R.id.btAddTodo) as Button
        btAddTodo!!.setOnClickListener(this::onClickAddTodo)

        tvBoardTitle = view.findViewById(R.id.tvBoardTitle) as TextView
        tvBoardDate = view.findViewById(R.id.tvBoardDate) as TextView
        rvTodos = view.findViewById(R.id.rvTodos) as RecyclerView

        populateUi()

    }

    private fun populateUi() {

        tvBoardTitle!!.text = "Quadro #1"
        tvBoardDate!!.text = "22/04/2017"

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val context = activity.applicationContext
        val layoutManager = LinearLayoutManager(context)

        adapter = TodoAdapter(this::updateTodo)
        adapter!!.todoList = listOf(
            Todo(1, "Learn Kotlin ", true),
            Todo(2, "Find yourself a girl, and settle down", false),
            Todo(3, "Live a simple life in a quiet town", false),
            Todo(4, "Steady as she goes (steady as she goes)", true)
        )

        rvTodos!!.adapter = adapter
        rvTodos!!.layoutManager = layoutManager

    }

    private fun onClickAddTodo(view: View) {
        val inflatedView = activity.layoutInflater.inflate(R.layout.dialog_add_todo, null)
        AlertDialog.Builder(activity)
            .setView(inflatedView)
            .setPositiveButton("Adicionar", { _, _ -> saveTodo(view) })
            .setNegativeButton("Cancelar", { dialog, _ ->  dialog.cancel() })
            .show()
    }

    private fun saveTodo(view: View) {

    }

    private fun updateTodo(button: CompoundButton, isChecked: Boolean, item: Todo): Unit {
        button.paintFlags = if (!isChecked) Paint.LINEAR_TEXT_FLAG else Paint.STRIKE_THRU_TEXT_FLAG
    }

}