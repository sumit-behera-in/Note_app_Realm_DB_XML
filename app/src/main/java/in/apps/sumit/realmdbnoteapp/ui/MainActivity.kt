package `in`.apps.sumit.realmdbnoteapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import `in`.apps.sumit.realmdbnoteapp.databinding.ActivityMainBinding
import `in`.apps.sumit.realmdbnoteapp.realmdbModels.Note
import `in`.apps.sumit.realmdbnoteapp.realmdbModels.RealmApplication
import `in`.apps.sumit.realmdbnoteapp.recyclerViewAdapters.MainActivityRecyclerViewAdapter
import io.realm.kotlin.ext.query

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val realm = RealmApplication.realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.myRecyclerView.layoutManager =GridLayoutManager(this,1)
        val data:ArrayList<Note> = ArrayList(realm.query<Note>().find())
        binding.myRecyclerView.adapter =MainActivityRecyclerViewAdapter(data,this@MainActivity,realm)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this,AddDataToRealmActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}