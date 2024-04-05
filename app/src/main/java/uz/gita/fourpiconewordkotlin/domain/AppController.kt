package uz.gita.fourpiconewordkotlin.domain

import android.util.Log
import uz.gita.fourpiconewordkotlin.R
import uz.gita.fourpiconewordkotlin.data.model.QuestionData
import uz.gita.fourpiconewordkotlin.data.source.MyShared
import java.util.Random

class AppController private constructor() {
    companion object {
        private val LETTERS = "QWERTYUIOPASDFGHJKLZXCVBNM".toList()
        private val ANSWER_BUTTON_LENGTH = 12
        private lateinit var controller: AppController
        fun init() {
            controller = AppController()
        }

        fun getInstance(): AppController = controller
    }

    private val shared = MyShared.getInstance()
    private var level = shared.getLevel()
    private var coins = shared.getCoins()
    private var helperCount = 0

    fun getHelperCount(): Int {
        return helperCount
    }

    fun getLevel(): Int {
        return level
    }

    fun decreaseHelperCount() {
        if (helperCount > 0) helperCount--
    }

    fun increaseCoins() {
        coins += 50
    }

    fun canChangeCoinsToHelper(): Boolean = coins >= 60

    fun changeCoinsToHelper() {
        if (coins >= 60) {
            coins -= 60
            helperCount++
        }
    }

    fun increaseHelperCount() {
        helperCount++
    }

    fun saveHelperCount() {
        shared.saveHelperCount(helperCount)
    }

    fun clearResult() {
        shared.clearResult()
    }

    fun increaseLevel() {
        if (!isFinish()) level++
        saveLevel()
    }

    fun isFinish(): Boolean = ls.size + 1 == level // 4 4

    fun saveLevel() {
        shared.saveLevel(level)
    }

    fun saveCoins() {
        shared.saveCoins(coins)
    }


    fun getCurrentQuestion(): QuestionData {
        level = shared.getLevel()
        return ls[level - 1]
    }

    fun getCoins(): Int {
        return coins
    }

    fun saveHelperAnswerToDeviceMemory(helperAnswer: MutableList<Int>) {
        if (helperAnswer.size == 0) return
        val sb = StringBuilder()
        for (name in helperAnswer) {
            sb.append(name).append("#")
        }

        shared.saveHelperAnswerToDeviceMemory(sb.toString())
    }

    fun getHelperAnswers(): MutableList<Int> {
        return shared.getHelperAnswers()
    }

    fun isLastLevel() = ls.size == level

    private var ls: List<QuestionData> = listOf(
        QuestionData(
            intArrayOf(
                R.drawable.level1_image1,
                R.drawable.level1_image2,
                R.drawable.level1_image3,
                R.drawable.level1_image4
            ), generateVariant(
                "dumaloq"
            ), "dumaloq".uppercase()
        ),
        QuestionData(
            intArrayOf(
                R.drawable.level2_image1,
                R.drawable.level2_image2,
                R.drawable.level2_image3,
                R.drawable.level2_image4
            ), generateVariant(
                "elektron"
            ), "elektron".uppercase()
        ),
        QuestionData(
            intArrayOf(
                R.drawable.level3_image1,
                R.drawable.level3_image2,
                R.drawable.level3_image3,
                R.drawable.level3_image4
            ), generateVariant(
                "micro"
            ), "micro".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4),
            generateVariant("mashina"),
            "mashina".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.big1, R.drawable.big2, R.drawable.big3, R.drawable.big4),
            generateVariant("katta"),
            "katta".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.fruits, R.drawable.fruits2, R.drawable.fruits3, R.drawable.fruits4), generateVariant(
                "mevalar"
            ), "mevalar".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.sleep1, R.drawable.sleep2, R.drawable.sleep3, R.drawable.sleep4), generateVariant(
                "uyqu"
            ), "uyqu".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.poizon1, R.drawable.poizon1, R.drawable.poizon2, R.drawable.poizon4), generateVariant(
                "zahar"
            ), "zahar".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.rubber1, R.drawable.rubber2, R.drawable.rubber3, R.drawable.rubber4),
            generateVariant(
                "rezina"
            ),
            "rezina".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.home1, R.drawable.home2, R.drawable.home3, R.drawable.home4),
            generateVariant("uy"),
            "uy".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.tooth1, R.drawable.tooth2, R.drawable.tooth3, R.drawable.tooth4), generateVariant(
                "tish"
            ), "tish".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.nose1, R.drawable.nose2, R.drawable.nose3, R.drawable.nose4),
            generateVariant("burun"),
            "burun".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.sword1, R.drawable.sword2, R.drawable.sword3, R.drawable.sword4), generateVariant(
                "qilich"
            ), "qilich".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.spoon1, R.drawable.spoon2, R.drawable.spoon3, R.drawable.spoon4), generateVariant(
                "qoshiq"
            ), "qoshiq".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.beard1, R.drawable.beard2, R.drawable.beard3, R.drawable.beard4), generateVariant(
                "soqol"
            ), "soqol".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.cold1, R.drawable.cold2, R.drawable.cold3, R.drawable.cold4),
            generateVariant("sovuq"),
            "sovuq".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.cross1, R.drawable.cross2, R.drawable.cross3, R.drawable.cross4), generateVariant(
                "kesishma"
            ), "kesishma".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.disk1, R.drawable.disk2, R.drawable.disk3, R.drawable.disk4),
            generateVariant("disk"),
            "disk".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.gates1, R.drawable.gates2, R.drawable.gates3, R.drawable.gates4), generateVariant(
                "darvoza"
            ), "darvoza".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.loud1, R.drawable.loud2, R.drawable.loud3, R.drawable.loud4),
            generateVariant("baland"),
            "baland".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.raw1, R.drawable.raw2, R.drawable.raw3, R.drawable.raw4),
            generateVariant("xom"),
            "xom".uppercase()
        ),
        QuestionData(
            intArrayOf(R.drawable.sweet1, R.drawable.sweet2, R.drawable.sweet3, R.drawable.sweet4), generateVariant(
                "shirin"
            ), "shirin".uppercase()
        ),
    )

    private fun generateVariant(answer: String): String {
        val random = Random()
        val ls = answer.uppercase().toList().toMutableList()

        while (ls.size < ANSWER_BUTTON_LENGTH) {
            ls.add(LETTERS[random.nextInt(LETTERS.size)])
        }

        val sb = StringBuilder()
        ls.shuffle()
        ls.forEach { sb.append(it) }

        return sb.toString()
    }
}

/*
  QuestionData(
            intArrayOf(
                R.drawable.apple, R.drawable.banana, R.drawable.grapes, R.drawable.pomegranate
            ), "asdhkaelimol", "shamol"
        ),

        QuestionData(
            intArrayOf(
                R.drawable.sleep_man, R.drawable.sleep_2, R.drawable.sleep_3, R.drawable.sleep_baby
            ), "qadffucdygau", "uyqu"
        ),

        QuestionData(
            intArrayOf(
                R.drawable.think1, R.drawable.think2, R.drawable.think3, R.drawable.think4
            ), "soie'hyiscla", "o'ylash"
        ),

        QuestionData(
            intArrayOf(
                R.drawable.think1, R.drawable.think2, R.drawable.think3, R.drawable.think4
            ), "irqsdonaqgo''", "qo'ng'iroq"
        ),
 */