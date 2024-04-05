package uz.gita.fourpiconewordkotlin.presentation.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import uz.gita.fourpiconewordkotlin.R
import uz.gita.fourpiconewordkotlin.presentation.finaldialog.FinalDialog

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var images: List<ImageView>
    private lateinit var answerButtons: List<AppCompatButton>
    private lateinit var variantButtons: List<AppCompatButton>
    private lateinit var viewBack: View
    private lateinit var dialogBack: AlertDialog
    private lateinit var helpButton: ImageView
    private lateinit var clearButton: ImageView
    private lateinit var coins: TextView
    private lateinit var level: TextView
    private lateinit var builder: AlertDialog.Builder
    private lateinit var noEnoughCoinsDialog: AlertDialog.Builder

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initImages()
        initAnswerButtons()
        initVariantButtons()
        initOtherButtons()
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            this.finish()
        }

        builder = AlertDialog.Builder(this)
        builder
            .setMessage("60 tanga evaziga yordam olasizmi ?")
            .setTitle("Yordam olish")
            .setPositiveButton("Ha") { dialog, _ ->
                presenter.onClickYes()
                dialog.dismiss()
            }.setCancelable(true)
            .setNegativeButton("Yo'q") { dialog, _ ->
                dialog.dismiss()
            }

        noEnoughCoinsDialog = AlertDialog.Builder(this)
        noEnoughCoinsDialog
            .setMessage("Sizda hali 60 coin to'planmagan")
            .setTitle("Yordam olish")
            .setPositiveButton("Yopish") { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(true)

        presenter = MainPresenter(this)
    }

    private fun initOtherButtons() {
        helpButton = findViewById(R.id.btnHelp)
        helpButton.setOnClickListener {
            presenter.onClickHelp()
        }

        clearButton = findViewById(R.id.btnClear)
        clearButton.setOnClickListener {
            presenter.onClickClearButton()
        }

        level = findViewById(R.id.levelCount)
        coins = findViewById(R.id.coins)

    }

    override fun onPause() {
        presenter.saveLevel()
        presenter.saveCoins()
        presenter.saveHelperCount()
        presenter.saveHelperAnswerToDeviceMemory()
        super.onPause()
    }

    override fun loadButtons(variantData: String) {
        for (i in variantButtons.indices) {
            val letter: String = variantData[i].toString()
            variantButtons[i].text = letter
        }
    }

    override fun loadImages(resource: IntArray) {
        for ((i, image) in this.images.withIndex()) {
            image.setImageResource(resource[i])
        }
    }

    override fun invisibleVariant(index: Int) {
        variantButtons[index].visibility = View.INVISIBLE
    }

    override fun visibleAllVariants() {
        for (i in variantButtons.indices) {
            variantButtons[i].visibility = View.VISIBLE
        }
    }

    override fun showVariantOnAnswer(index: Int, letter: String) {
        answerButtons[index].text = letter
    }

    override fun showHelperAnswer(index: Int, letter: String) {
        answerButtons[index].text = letter
        answerButtons[index].setBackgroundResource(R.color.blue)
    }

    override fun removeFromAnswer(index: Int) {
        answerButtons[index].text = ""
    }

    override fun addToVariant(index: Int) {
        variantButtons[index].visibility = View.VISIBLE
    }

    override fun clearAnswersText() {
        for (i in answerButtons.indices) {
            answerButtons[i].text = ""
        }
    }

    override fun clearHelpersBackground() {
        for (i in answerButtons.indices) {
            answerButtons[i].setBackgroundResource(R.color.green)
        }
    }

    override fun openMenuScreen() {
        finish()
    }

    override fun openLevelDialog(isFinish: Boolean) {

        viewBack = LayoutInflater.from(this).inflate(R.layout.my_dialog, null, false)
        dialogBack = AlertDialog.Builder(this).setView(viewBack).setCancelable(false).create()

        viewBack.findViewById<AppCompatButton>(R.id.btnNext).setOnClickListener {
            presenter.onClickNext()
            dialogBack.dismiss()
        }

        viewBack.findViewById<AppCompatButton>(R.id.btnHome).setOnClickListener {
            dialogBack.dismiss()
            presenter.onClickHome()
        }

        dialogBack.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogBack.show()

    }

    override fun fixAnswerLength(length: Int) {
        for (i in 0 until length) {
            answerButtons[i].visibility = View.VISIBLE
        }
        for (i in length until answerButtons.size) {
            answerButtons[i].visibility = View.GONE
        }

        answerButtons.forEachIndexed { index, button ->
            button.visibility = if (index < length) View.VISIBLE else View.GONE
        }
    }

    private fun initVariantButtons() {
        variantButtons = ArrayList()
        val layout1 = findViewById<LinearLayoutCompat>(R.id.variantButtons1)
        val layout2 = findViewById<LinearLayoutCompat>(R.id.variantButtons2)

        for (i in 0 until layout1.childCount) {
            val button = layout1.getChildAt(i) as AppCompatButton
            button.tag = i
            button.setOnClickListener {
                presenter.onClickVariantButton(i, button.text.toString())
            }
            variantButtons += button
        }

        val count = layout1.childCount
        for (i in 0 until layout2.childCount) {
            val button = layout2.getChildAt(i) as AppCompatButton
            button.tag = i + count
            button.setOnClickListener {
                presenter.onClickVariantButton(i + count, button.text.toString())
            }
            variantButtons += button
        }
    }

    private fun initAnswerButtons() {
        answerButtons = ArrayList()
        val layout = findViewById<LinearLayoutCompat>(R.id.answerButtons)

        for (i in 0 until layout.childCount) {
            val button = layout.getChildAt(i) as AppCompatButton
            answerButtons += button
            button.setOnClickListener {
                presenter.onClickAnswerButton(i)
            }
        }
    }

    private fun initImages() {
        images = ArrayList()
        images += findViewById<ImageView>(R.id.question1)
        images += findViewById<ImageView>(R.id.question2)
        images += findViewById<ImageView>(R.id.question3)
        images += findViewById<ImageView>(R.id.question4)
    }


    override fun setLevel(level: Int) {
        this.level.text = level.toString()
    }

    override fun setCoins(coins: Int) {
        this.coins.text = coins.toString()
    }

    override fun openDialog() {
        builder.show()
    }

    override fun openNoEnoughCoinsDialog() {
        noEnoughCoinsDialog.show()
    }

    override fun openFinalDialog() {
        val dialog = FinalDialog()
        dialog.setListener {
            presenter.clearResult()
        }
        dialog.show(supportFragmentManager, "")
    }
}