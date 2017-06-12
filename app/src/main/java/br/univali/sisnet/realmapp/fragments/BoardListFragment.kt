package br.univali.sisnet.realmapp.fragments

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import br.univali.sisnet.realmapp.R
import br.univali.sisnet.realmapp.domain.Board
import br.univali.sisnet.realmapp.view.adapters.BoardAdapter
import io.realm.Realm
import br.univali.sisnet.realmapp.*

class BoardListFragment : Fragment() {

    var rvBoards: RecyclerView? = null
    var fabAddBoard: FloatingActionButton? = null

    var adapter: BoardAdapter? = null
    var listener: OnItemSelectedListener? = null

    val realm = Realm.getDefaultInstance()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
          savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_board_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvBoards = view!!.findViewById(R.id.rvBoards) as RecyclerView?

        fabAddBoard = view.findViewById(R.id.fabAddBoard) as FloatingActionButton?
        fabAddBoard!!.setOnClickListener(this::onClickAddBoard)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val context = activity.applicationContext
        val layoutManager = LinearLayoutManager(context)

        adapter = BoardAdapter(listener)
        adapter!!.boardList = realm?.where(Board::class.java)!!.findAll()

        rvBoards!!.layoutManager = layoutManager
        rvBoards!!.adapter = adapter

    }

    private fun onClickAddBoard(view: View) {
        val inflatedView = activity.layoutInflater.inflate(R.layout.dialog_add_board, null)
        AlertDialog.Builder(activity)
            .setView(inflatedView)
            .setPositiveButton("Adicionar", { _, _ -> saveBoard(inflatedView) })
            .setNegativeButton("Cancelar", { dialog, _ -> dialog.cancel() })
            .show()
    }

    private fun saveBoard(view: View) {

        val etBoardName = view.findViewById(R.id.etBoardName) as EditText
        val board: Board = Board()

        board.id = realm.getNextId(board, "id")
        board.name = etBoardName.text.toString()

        realm?.executeTransaction { realm -> realm.copyToRealm(board) }

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
