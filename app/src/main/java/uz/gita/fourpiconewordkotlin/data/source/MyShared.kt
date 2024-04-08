package uz.gita.fourpiconewordkotlin.data.source

import android.content.Context
import android.content.SharedPreferences

class MyShared private constructor(context: Context) {

    companion object {
        private var myShared: MyShared? = null
        private lateinit var pref: SharedPreferences

        fun getInstance(): MyShared {
            return myShared!!
        }

        fun init(context: Context) {
            myShared = MyShared(context)
        }

    }

    init {
        pref = context.getSharedPreferences("TestKotlin", Context.MODE_PRIVATE)
    }

    fun saveLevel(level: Int) {
        pref.edit().putInt("LEVEL", level)?.apply()
    }

    fun getLevel(): Int {
        return pref.getInt("LEVEL", 1);
    }

    fun clearResult() {
        pref.edit().clear().apply()
    }

    fun getCoins(): Int {
        return pref.getInt("COINS", 150)
    }

    fun saveCoins(currentCoins: Int) {
        pref.edit().putInt("COINS", currentCoins).apply()
    }

    fun getHelperCount(): Int {
        return pref.getInt("HELPER_COUNT", 1);
    }

    fun saveHelperCount(helperCount: Int) {
        pref.edit().putInt("HELPER_COUNT", helperCount).apply()
    }

    fun saveHelperAnswerToDeviceMemory(helperAnswers: String) {
        pref.edit().putString("HELPER_ANSWERS", helperAnswers).apply()
    }

    fun getHelperAnswers(): MutableList<Int> {
        val s = pref.getString("HELPER_ANSWERS", "")
        if (s == "") return mutableListOf()

        val ss = s!!.split("#")
        val res = mutableListOf<Int>()

        for (str in ss) {
            if (str != "#") {
                res.add(str.toInt())
            }
        }

        return res
    }
}


//class MyShared private constructor() {
//    companion object {
//
//        @Volatile
//        private var instance: MyShared? = null
//        private lateinit var pref: SharedPreferences
//
//        fun getInstance() =
//            instance ?: synchronized(this) {
//                instance ?: MyShared().also { instance = it }
//            }
//    }
//
//    fun doSomething() = "Doing something"
//}