package br.univali.sisnet.realmapp.domain

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

class Board(
    @PrimaryKey var id: Long = 0,
    var name: String = "",
    var todos: RealmList<Todo> = RealmList(),
    val createdAt: Date = Date()
) : RealmObject()
