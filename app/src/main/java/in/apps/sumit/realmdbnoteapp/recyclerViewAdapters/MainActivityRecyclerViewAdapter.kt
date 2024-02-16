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

class MainActivityRecyclerViewAdapter(val data:List<Note>, val context: Context):RecyclerView.Adapter<MainActivityRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater  = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.single_note,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val note :Note = data[position]
        holder.data.text = note.content
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        holder.date.text = sdf.format(note.date).toString()
        holder.heading.text = note.title
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val heading : TextView = itemView.findViewById(R.id.heading)
        val data :TextView = itemView.findViewById(R.id.content)
        val date:TextView = itemView.findViewById(R.id.date)
    }
}