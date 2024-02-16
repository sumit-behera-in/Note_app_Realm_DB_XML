package `in`.apps.sumit.realmdbnoteapp.recyclerViewAdapters

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.apps.sumit.realmdbnoteapp.R
import `in`.apps.sumit.realmdbnoteapp.realmdbModels.Note
import io.realm.kotlin.Realm

class MainActivityRecyclerViewAdapter(val data:List<Note>, val context: Context,val realm: Realm):RecyclerView.Adapter<MainActivityRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater  = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.single_note,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val note :Note = data[position]
        holder.content.text = note.content
        holder.date.text = note.date
        holder.heading.text = note.title


    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val heading : TextView = itemView.findViewById(R.id.heading)
        val content :TextView = itemView.findViewById(R.id.content)
        val date:TextView = itemView.findViewById(R.id.date)
    }
}