package br.univali.sisnet.realmapp.view.holders

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import br.univali.sisnet.realmapp.R
import br.univali.sisnet.realmapp.domain.Todo

class TodoHolder(
    itemView: View?,
    var listener: (CompoundButton, Boolean, Todo) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    var context: Context? = null
    var cbTodoCompleted: CheckBox? = null

    init {
        cbTodoCompleted = itemView!!.findViewById(R.id.cbTodoCompleted) as CheckBox
        context = itemView.context
    }

    fun bindItem(item: Todo) {

        cbTodoCompleted!!.text = item.description

        if (item.completed) {
            cbTodoCompleted!!.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        cbTodoCompleted!!.isChecked = item.completed
        cbTodoCompleted!!.setOnCheckedChangeListener {
            buttonView, isChecked -> listener(buttonView, isChecked, item)
        }
    }
}