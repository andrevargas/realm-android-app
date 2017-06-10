package br.univali.sisnet.realmapp.view.holders

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

    init {
        tvName = itemView!!.findViewById(R.id.tvName) as TextView?
        tvTodoCount = itemView.findViewById(R.id.tvTodoCount) as TextView?
    }

    fun bindItem(item: Board) {
        tvName!!.text = item.name
        tvTodoCount!!.text = item.todos.size.toString()
        itemView.setOnClickListener { listener!!.onItemSelected(item) }
    }
}
