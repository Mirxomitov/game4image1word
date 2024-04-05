package uz.gita.fourpiconewordkotlin.presentation.menu

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.cardview.widget.CardView
import uz.gita.fourpiconewordkotlin.R
import uz.gita.fourpiconewordkotlin.presentation.main.MainActivity

class MenuActivity : AppCompatActivity(), MenuContract.View {

    private lateinit var presenter: MenuContract.Presenter
    private var menuImages = mutableListOf<ImageView>()
    private lateinit var level: TextView
    private lateinit var coins: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        initButtons()
        findImages()
        findTexts()
        presenter = MenuPresenter(this)
    }

    private fun findTexts() {
        level = findViewById(R.id.menuLevel)
        coins = findViewById(R.id.menuCoins)

    }

    private fun findImages() {
        menuImages.add(findViewById(R.id.image1))
        menuImages.add(findViewById(R.id.image2))
        menuImages.add(findViewById(R.id.image3))
        menuImages.add(findViewById(R.id.image4))
    }


    private fun initButtons() {
        findViewById<CardView>(R.id.btnPlay).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<AppCompatImageButton>(R.id.btnClear).setOnClickListener {
            presenter.clearResult()
        }
    }

    override fun showImages(images: IntArray) {
        for ((index, imageView) in menuImages.withIndex()) {
            imageView.setImageResource(images[index])
        }
    }

    override fun updateLevel(levelText: String) {
        level.text = levelText
    }

    override fun updateCoins(coinsText: String) {
        coins.text = coinsText
    }

    override fun onResume() {
        presenter.setImages()
        presenter.setCoins()
        presenter.setLevel()
        super.onResume()
    }
}