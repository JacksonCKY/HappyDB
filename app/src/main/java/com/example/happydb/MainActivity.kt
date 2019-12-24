package com.example.happydb

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var feelingViewModel: FeelingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recycleView:RecyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter: FeelingAdapter = FeelingAdapter(this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        feelingViewModel = ViewModelProvider(this).get(FeelingViewModel::class.java)

        feelingViewModel.allFeelings.observe(
            this,
            Observer { FeelingList: ->
                FeelingList?.let{
                    if(it.isNotEmpty()){
                       adapter.setFeeling(it)
                        Toast.makeText(applicationContext, "Num" + it.size, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        )
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
