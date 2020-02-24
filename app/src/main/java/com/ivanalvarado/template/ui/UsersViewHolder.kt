package com.ivanalvarado.template.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivanalvarado.template.R
import com.ivanalvarado.template.database.entity.UserEntity

class UsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.user_item_name)
    private val reputation: TextView = view.findViewById(R.id.user_item_reputation)
    private val profileImage: ImageView = view.findViewById(R.id.user_item_profile_image)

    fun bind(user: UserEntity?) {
        if (user == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
        } else {
            showUserData(user)
        }
    }

    private fun showUserData(user: UserEntity) {
        name.text = user.userName
        reputation.text = user.reputation.toString()
        Glide.with(itemView).load(user.imageUrl).into(profileImage)
    }

    companion object {
        fun create(parent: ViewGroup): UsersViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_user_item, parent,false)
            return UsersViewHolder(view)
        }
    }
}