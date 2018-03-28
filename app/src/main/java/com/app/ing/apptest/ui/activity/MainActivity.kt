package com.app.ing.apptest.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.Toast
import com.app.ing.apptest.R
import com.app.ing.apptest.data.UserApi
import com.app.ing.apptest.database.DatabaseHelper
import com.app.ing.apptest.ui.adapters.UserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recyclerView = find<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)

         val db = DatabaseHelper(this)

        UserApi.createUserService()
                .getUsers( 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    for (item in response.results) {
                        db.insertUserData(item.name.title+" "+item.name.first+" "+item.name.last,
                                item.picture.medium,item.phone,
                                item.email,item.location.city+","+item.location.state+","+item.location.street)
                    }
                    recyclerView.adapter =UserAdapter(db.getAllUserData());


                    // val name: String = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME))
                    Toast.makeText(this, "Total : ${db.getAllUserData().size}", Toast.LENGTH_LONG).show()
                })


       // Toast.makeText(this,"OK ${db.getAllUserData().size}", Toast.LENGTH_LONG).show();

    }

    override fun onResume() {
        super.onResume()
    }


}
