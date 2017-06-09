package br.univali.sisnet.realmapp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.univali.sisnet.realmapp.domain.Board
import br.univali.sisnet.realmapp.view.holders.BoardHolder

class BoardAdapter : RecyclerView.Adapter<BoardHolder>() {

    var boardList: List<Board>? = null;

    override fun getItemCount(): Int {
        return boardList!!.size;
    }

    override fun onBindViewHolder(holder: BoardHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BoardHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}