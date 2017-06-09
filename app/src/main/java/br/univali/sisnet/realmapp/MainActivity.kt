package br.univali.sisnet.realmapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.univali.sisnet.realmapp.fragments.BoardListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val boardListFragment = BoardListFragment()

        transaction.replace(R.id.flMain, boardListFragment)
        transaction.commit()

    }

}
