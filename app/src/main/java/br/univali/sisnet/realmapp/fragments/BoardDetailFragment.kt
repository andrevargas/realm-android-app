package br.univali.sisnet.realmapp.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import br.univali.sisnet.realmapp.R

class BoardDetailFragment : Fragment() {

    var tvBoardTitle: TextView? = null
    var btAddTodo: Button? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_board_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btAddTodo = view!!.findViewById(R.id.btAddTodo) as Button
        btAddTodo!!.setOnClickListener(this::onClickAddTodo)
    }

    private fun onClickAddTodo(view: View) {
        AlertDialog.Builder(activity)
            .setView(activity.layoutInflater.inflate(R.layout.dialog_add_todo, null))
            .setPositiveButton("Adicionar", { dialog, which ->  })
            .setNegativeButton("Cancelar", { dialog, which ->  })
            .show()
    }

}