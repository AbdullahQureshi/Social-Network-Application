package com.globalrescue.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.globalrescue.R
import com.globalrescue.data.entity.PostCommentsModel

class PostCommentAdapter(private var dataSet: ArrayList<PostCommentsModel>) :
    RecyclerView.Adapter<PostCommentAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val email: TextView
        val body: TextView

        init {
            // Define click listener for the ViewHolder's View
            name = view.findViewById(R.id.tv_name)
            email = view.findViewById(R.id.tv_email)
            body = view.findViewById(R.id.tv_body)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_post_comment, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        dataSet?.get(position)?.let {
            viewHolder.name.text = dataSet.get(position).name
            viewHolder.email.text = dataSet.get(position).email
            viewHolder.body.text = dataSet.get(position).body


        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}