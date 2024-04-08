package uz.gita.fourpiconewordkotlin.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.fourpiconewordkotlin.R
import uz.gita.fourpiconewordkotlin.presentation.menu.MenuActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(300)
            val intent = Intent(this@SplashActivity, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}