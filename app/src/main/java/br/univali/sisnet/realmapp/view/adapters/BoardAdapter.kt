package br.univali.sisnet.realmapp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.univali.sisnet.realmapp.R
import br.univali.sisnet.realmapp.domain.Board
import br.univali.sisnet.realmapp.fragments.BoardListFragment
import br.univali.sisnet.realmapp.view.holders.BoardHolder

class BoardAdapter(
    val listener: BoardListFragment.OnItemSelectedListener?
) : RecyclerView.Adapter<BoardHolder>() {

    var boardList: List<Board>? = null;

    override fun getItemCount(): Int {
        return boardList!!.size
    }

    override fun onBindViewHolder(holder: BoardHolder?, position: Int) {
        holder!!.bindItem(boardList!![position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BoardHolder {
        val view = LayoutInflater
            .from(parent!!.context)
            .inflate(R.layout.item_board, parent, false)
        return BoardHolder(view, listener)
    }

}
