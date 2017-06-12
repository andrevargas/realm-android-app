package br.univali.sisnet.realmapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmModel

class RealmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}

fun Realm.getNextId(model: RealmModel, idField: String): Long {
    try {
        return where(model::class.java)?.max(idField)!!.toLong() + 1
    } catch (ex: ArrayIndexOutOfBoundsException) {
        return 1
    } catch (ex: NullPointerException) {
        return 1
    }
}