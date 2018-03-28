package com.app.ing.apptest.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.ing.apptest.R
import com.app.ing.apptest.model.User
import com.app.ing.apptest.model.UserDatabase
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * Created by jose on 27/03/18.
 */

class UserAdapter(private val userList: ArrayList<UserDatabase>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder?, position: Int) {
        holder?.bind(userList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.user_list_container, parent, false)
        return UserViewHolder(view)
    }


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgProfile = view.find<ImageView>(R.id.imgProfile)
        private val tvName = view.find<TextView>(R.id.tvNombre)
        private val tvLocation = view.find<TextView>(R.id.tvLocation)
        private val tvEmail = view.find<TextView>(R.id.tvEmail)
        private val tvPhone = view.find<TextView>(R.id.tvPhone)

        fun bind(user: UserDatabase) {
            with(user) {
                Picasso.with(itemView.context).load(imgUrl).into(imgProfile)
                tvName.text = name.toString()
                tvEmail.text = email.toString()
                tvLocation.text = location.toString()
                tvPhone.text = phone.toString()
            }
        }

    }
}

