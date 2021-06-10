package com.trinityempire.capsulenytimes.ui.gridview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.trinityempire.capsulenytimes.R
import com.trinityempire.capsulenytimes.model.entities.MovieCriticsResults
import timber.log.Timber

class GridViewAdapter :
    RecyclerView.Adapter<GridViewAdapter.ViewHolder>() {

    companion object {
        private val IMAGE_VIEW_WIDTH = 100
        private val IMAGE_VIEW_HEIGHT = 100
    }
    private var dataSet: ArrayList<MovieCriticsResults> = ArrayList()

    private lateinit var userClickListener: GridViewAdapterListener
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textView)
            imageView = view.findViewById(R.id.imageView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        Timber.d("On Create View Holder")
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.grid_view_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Timber.d("On Bind View Holder")
        //Timber.d(dataSet[position].display_name)
        Timber.d(dataSet[position].multimedia?.resource?.src)

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].display_name

        // Using Picasso Square library to load image and receice to 100x100
        Picasso.get()
            .load(dataSet[position].multimedia?.resource?.src)
            .resize(IMAGE_VIEW_WIDTH, IMAGE_VIEW_HEIGHT)
            .placeholder(R.drawable.placeholder)
            .centerCrop()
            .into(viewHolder.imageView)

        viewHolder.itemView.setOnClickListener {
            Timber.d("User clicked listener")
            dataSet.get(position).let { it1 -> userClickListener.onUserClickListener(it1) }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun updateCriticsList(data: List<MovieCriticsResults>) {
        Timber.d("Update Critics List")
        // when user refresh, we would want to remove all data in dataSet list.
        this.dataSet.clear()
        this.dataSet.trimToSize()
        this.dataSet.addAll(data)

        notifyDataSetChanged()
    }

    fun setListener(listener: GridViewAdapterListener) {
        Timber.d("Set Listener")
        userClickListener = listener
    }
}
