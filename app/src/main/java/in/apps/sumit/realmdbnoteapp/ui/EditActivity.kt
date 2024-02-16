package `in`.apps.sumit.realmdbnoteapp.ui

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import `in`.apps.sumit.realmdbnoteapp.databinding.ActivityEditBinding
import `in`.apps.sumit.realmdbnoteapp.realmdbModels.Note
import `in`.apps.sumit.realmdbnoteapp.realmdbModels.RealmApplication.Companion.realm
import io.realm.kotlin.ext.query
import java.util.Date

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Edit The Note"

        val bundle : Bundle? = intent.extras

        if (bundle == null) {
            val intent = Intent(this,AddDataToRealmActivity::class.java)
            startActivity(intent)
            finish()
        }
        val title = bundle?.getString("title")
        val content = bundle?.getString("content")
        val date = bundle?.getString("date")
        binding.editTitle.text = Editable.Factory.getInstance().newEditable(title)
        binding.editContent.text = Editable.Factory.getInstance().newEditable(content)


        binding.done.setOnClickListener {
            if(binding.editContent.text.toString() != "" || binding.editTitle.text.toString() != ""){
                val query = realm.query<Note>("date == $0 && content == $1 && title == $2",date,content,title).find().first()
                query.also { it ->
                    realm.writeBlocking {
                        val sdf = SimpleDateFormat("dd/MM/yyyy  hh:mm")

                        findLatest(it)?.title = binding.editTitle.text.toString()
                        findLatest(it)?.content = binding.editContent.text.toString()
                        findLatest(it)?.date =sdf.format(Date()).toString()
                    }
                }

                Toast.makeText(this,"Done", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@EditActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Invalid Inputs", Toast.LENGTH_SHORT).show()
            }

        }

        binding.cancel.setOnClickListener {
            val intent = Intent(this@EditActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}