package br.univali.sisnet.realmapp.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.Date

open class Todo(
    @PrimaryKey var id: Long = 0,
    var description: String = "",
    var completed: Boolean = false,
    var createdAt: Date = Date()
) : RealmObject(), Serializable
