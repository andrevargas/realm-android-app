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
import android.widget.*
import br.univali.sisnet.realmapp.R
import br.univali.sisnet.realmapp.domain.Board
import br.univali.sisnet.realmapp.domain.Todo
import br.univali.sisnet.realmapp.view.adapters.TodoAdapter
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*
import br.univali.sisnet.realmapp.*

class BoardDetailFragment : Fragment() {

    var rvTodos: RecyclerView? = null
    var tvBoardTitle: TextView? = null
    var tvBoardDate: TextView? = null
    var btAddTodo: Button? = null

    var adapter: TodoAdapter? = null
    var board: Board? = null

    val realm = Realm.getDefaultInstance()

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

        if (arguments == null) return

        val boardId = arguments.getLong("board_id")
        board = realm.where(Board::class.java).equalTo("id", boardId).findFirst()

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))

        tvBoardTitle!!.text = resources.getString(R.string.board_title, board?.id.toString(), board?.name)
        tvBoardDate!!.text = dateFormat.format(board?.createdAt)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val context = activity.applicationContext
        val layoutManager = LinearLayoutManager(context)

        adapter = TodoAdapter(this::updateTodo)
        adapter!!.todoList = board?.todos

        rvTodos!!.adapter = adapter
        rvTodos!!.layoutManager = layoutManager

    }

    private fun onClickAddTodo(view: View) {
        val inflatedView = activity.layoutInflater.inflate(R.layout.dialog_add_todo, null)
        AlertDialog.Builder(activity)
            .setView(inflatedView)
            .setPositiveButton("Adicionar", { _, _ -> saveTodo(inflatedView) })
            .setNegativeButton("Cancelar", { dialog, _ ->  dialog.cancel() })
            .show()
    }

    private fun saveTodo(view: View) {

        val etTodoDescription = view.findViewById(R.id.etTodoDescription) as EditText
        val cbTodoCompleted = view.findViewById(R.id.cbTodoCompleted) as CheckBox

        val todo = Todo()

        todo.id = realm.getNextId(todo, "id")
        todo.description = etTodoDescription.text.toString()
        todo.completed = cbTodoCompleted.isChecked

        realm.executeTransaction { realm ->
            board?.todos?.add(todo)
            realm.copyToRealmOrUpdate(board)
        }

    }

    private fun updateTodo(button: CompoundButton, isChecked: Boolean, item: Todo): Unit {

        realm.executeTransaction { realm ->
            item.completed = isChecked
            realm.copyToRealmOrUpdate(item)
        }

        button.paintFlags = if (!isChecked) Paint.LINEAR_TEXT_FLAG else Paint.STRIKE_THRU_TEXT_FLAG

    }

}