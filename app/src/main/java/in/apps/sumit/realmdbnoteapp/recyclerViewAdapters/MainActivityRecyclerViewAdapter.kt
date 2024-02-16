package `in`.apps.sumit.realmdbnoteapp.recyclerViewAdapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import `in`.apps.sumit.realmdbnoteapp.R
import `in`.apps.sumit.realmdbnoteapp.realmdbModels.Note
import io.realm.kotlin.Realm
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query

class MainActivityRecyclerViewAdapter(val data:ArrayList<Note>, val context: Context,val realm: Realm):RecyclerView.Adapter<MainActivityRecyclerViewAdapter.Holder>() {
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

        holder.itemView.setOnLongClickListener {
            delete(note,position)
            true
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun delete(note: Note,i:Int){
        realm.writeBlocking {
            findLatest(note)?.also { delete(it) }
        }
        data.removeAt(i)
        notifyDataSetChanged()
        Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show()
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