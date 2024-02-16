package `in`.apps.sumit.realmdbnoteapp.ui

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.lifecycleScope
import `in`.apps.sumit.realmdbnoteapp.databinding.ActivityAddDataToRealmBinding
import `in`.apps.sumit.realmdbnoteapp.realmdbModels.Note
import `in`.apps.sumit.realmdbnoteapp.realmdbModels.RealmApplication
import io.realm.kotlin.Realm
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch
import java.util.Date

class AddDataToRealmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDataToRealmBinding
    private val realm :Realm = RealmApplication.realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataToRealmBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.cancel.setOnClickListener {
            binding.editContent.clearComposingText()
            binding.editTitle.clearComposingText()

            val intent = Intent(this@AddDataToRealmActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.done.setOnClickListener {

            // write to realm db

            lifecycleScope.launch {
                realm.write {
                    val note = Note().apply {
                        val sdf = SimpleDateFormat("dd/MM/yyyy  hh:mm")

                        content = binding.editContent.text.toString()
                        date = sdf.format(Date()).toString()
                        title = binding.editTitle.text.toString()
                    }

                    copyToRealm(note)
                }
            }


            val intent = Intent(this@AddDataToRealmActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        lifecycleScope.launch {
            realm.write {
                val note = Note().apply {
                    val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")

                    content = binding.editContent.text.toString()
                    date = sdf.format(Date()).toString()
                    title = binding.editTitle.text.toString()
                }
            }
        }
    }


}