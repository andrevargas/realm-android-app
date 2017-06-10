package br.univali.sisnet.realmapp.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.univali.sisnet.realmapp.R
import br.univali.sisnet.realmapp.domain.Board
import br.univali.sisnet.realmapp.view.adapters.BoardAdapter

class BoardListFragment : Fragment() {

    var rvBoards: RecyclerView? = null
    var adapter: BoardAdapter? = null
    var listener: OnItemSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
          savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_board_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvBoards = view!!.findViewById(R.id.rvBoards) as RecyclerView?
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val context = activity.applicationContext
        val layoutManager = LinearLayoutManager(context)

        adapter = BoardAdapter(listener)
        adapter!!.boardList = listOf()

        rvBoards!!.layoutManager = layoutManager
        rvBoards!!.adapter = adapter

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnItemSelectedListener) {
            listener = context as OnItemSelectedListener?
        } else {
            throw ClassCastException(context.toString()
                + "must implement BoardListFragment.OnItemSelectedListener")
        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(item: Board): Unit
    }

}
