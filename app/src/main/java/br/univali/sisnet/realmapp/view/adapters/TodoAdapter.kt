package br.univali.sisnet.realmapp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import br.univali.sisnet.realmapp.R
import br.univali.sisnet.realmapp.domain.Todo
import br.univali.sisnet.realmapp.view.holders.TodoHolder

class TodoAdapter(
    val listener: (CompoundButton, Boolean, Todo) -> Unit
) : RecyclerView.Adapter<TodoHolder>() {

    var todoList: List<Todo>? = null

    override fun getItemCount(): Int {
        return todoList!!.size
    }

    override fun onBindViewHolder(holder: TodoHolder?, position: Int) {
        holder!!.bindItem(todoList!![position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TodoHolder {
        val view = LayoutInflater
            .from(parent!!.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoHolder(view, listener)
    }

}