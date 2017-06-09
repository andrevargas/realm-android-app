package br.univali.sisnet.realmapp.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

class Todo(
    @PrimaryKey var id: Long = 0,
    var description: String = "",
    var completed: Boolean = false,
    val createdAt: Date = Date()
) : RealmObject()