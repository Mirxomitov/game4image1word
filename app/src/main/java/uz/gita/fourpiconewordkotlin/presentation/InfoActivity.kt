package uz.gita.fourpiconewordkotlin.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import uz.gita.fourpiconewordkotlin.R

class InfoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        findViewById<ImageView>(R.id.telegram).setOnClickListener {
            openUrl("https://t.me/Mirxomitov")
        }
        findViewById<ImageView>(R.id.instagram).setOnClickListener {
            openUrl("https://www.instagram.com/mirxomitovportfolio")
        }
        findViewById<ImageView>(R.id.gmail).setOnClickListener {
            openUrl("mailto:mirxtohir@gmail.com")
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun openUrl(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}