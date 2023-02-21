package com.globalrescue.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.globalrescue.R
import com.globalrescue.data.entity.PostModel

class PostAdapter(private var dataSet: ArrayList<PostModel>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var mClickListener: ItemClickListener? = null

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val body: TextView

        init {
            // Define click listener for the ViewHolder's View
            title = view.findViewById(R.id.tv_title)
            body = view.findViewById(R.id.tv_body)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_post, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        dataSet?.get(position)?.let {
            viewHolder.title.text = dataSet.get(position).title
            viewHolder.body.text = dataSet.get(position).body
            viewHolder.itemView.setOnClickListener {
                mClickListener?.onItemClick(position)
            }
        }


    }

    fun updateList(dataSet: ArrayList<PostModel>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    open fun setClickListener(itemClickListener: ItemClickListener) {
        mClickListener = itemClickListener
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}