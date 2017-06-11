package br.univali.sisnet.realmapp.view.holders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.univali.sisnet.realmapp.R
import br.univali.sisnet.realmapp.domain.Board
import br.univali.sisnet.realmapp.fragments.BoardListFragment

class BoardHolder(
    itemView: View?,
    val listener: BoardListFragment.OnItemSelectedListener?
) : RecyclerView.ViewHolder(itemView) {

    var tvName: TextView? = null
    var tvTodoCount: TextView? = null
    val context: Context

    init {
        tvName = itemView!!.findViewById(R.id.tvName) as TextView?
        tvTodoCount = itemView.findViewById(R.id.tvTodoCount) as TextView?
        context = itemView.context
    }

    fun bindItem(item: Board) {
        val res = context.resources
        tvName!!.text = res.getString(R.string.board_title, item.id, item.name)
        tvTodoCount!!.text = res.getString(R.string.board_todo_count, item.todos.size.toString())
        itemView.setOnClickListener { listener!!.onItemSelected(item) }

    }
}
