package com.example.project
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TweetAdapter(val tweet: List<Tweet>) : RecyclerView.Adapter<TweetAdapter.ViewHolder>(){
    //create view holder that allow user to pull out the information about the user
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.name)

        val content: TextView = itemView.findViewById(R.id.content)

        val handle: TextView = itemView.findViewById(R.id.summit)

        val icon: ImageView = itemView.findViewById(R.id.androidImg)

    }
    //when adapter render the row, it need to know what xml file to use
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_tweet, parent, false)
        return ViewHolder(view)
    }

    //the adapter have the row to supply and need to be fill with data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTweet = tweet[position]
        holder.username.text = currentTweet.username
        holder.content.text = currentTweet.content
        holder.handle.text = currentTweet.handle

        //load the image
        Picasso.get()
            .load(currentTweet.iconUrl)
            .into(holder.icon)

        //retrieve the url when the button click from card view summit button
        holder.handle.setOnClickListener {
            val retrieveURL = currentTweet.handle
            val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(retrieveURL))
            holder.handle.context.startActivity(intent)
            
        }
    }


    //return total row
    override fun getItemCount(): Int {
        return tweet.size
    }

}