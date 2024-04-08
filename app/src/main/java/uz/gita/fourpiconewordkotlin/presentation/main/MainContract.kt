package uz.gita.fourpiconewordkotlin.presentation.main

import uz.gita.fourpiconewordkotlin.data.model.QuestionData

interface MainContract {
    interface View {
        fun loadButtons(variantData: String)
        fun loadImages(resource: IntArray)
        fun fixAnswerLength(length: Int)
        fun invisibleVariant(index: Int)
        fun showVariantOnAnswer(index: Int, letter: String)
        fun showHelperAnswer(index: Int, letter: String)
        fun removeFromAnswer(index: Int)
        fun addToVariant(index: Int)
        fun openMenuScreen()
        fun openLevelDialog(isFinish: Boolean)
        fun visibleAllVariants()
        fun clearAnswersText()
        fun clearHelpersBackground()
        fun setLevel(level: Int)
        fun setCoins(coins: Int)
        fun showConvertDialog()
        fun openNoEnoughCoinsDialog()
        fun openFinalDialog()
        fun showHelperDialog(message : String)
    }

    interface Model {
        fun getLevel(): Int
        fun saveLevel()
        fun saveCoins()
        fun saveHelperCount()
        fun getCurrentQuestion(): QuestionData
        fun increaseLevel()
        fun isFinish(): Boolean
        fun decreaseHelperCount()
        fun increaseHelperCount()
        fun increaseCoins()
        fun getHelperCount(): Int
        fun getCoins(): Int
        fun canConvertCoinsToHelper(): Boolean
        fun convertCoinsToHelper()
        fun saveHelperAnswerToDeviceMemory(helperAnswers : MutableList<Int>)
        fun getHelperAnswers(): MutableList<Int>
        fun clearResult()
        fun lastLevel(): Boolean
    }

    interface Presenter {
        fun onClickHelp(isSecondTime : Boolean)
        fun onClickNext()
        fun onClickHome()
        fun saveLevel()
        fun saveCoins()
        fun saveHelperCount()
        fun onClickVariantButton(index: Int, letter: String)
        fun onClickAnswerButton(index: Int)
        fun onClickYes()
        fun onClickClearButton()
        fun saveHelperAnswerToDeviceMemory()
        fun setHelperAnswersToView()
        fun clearResult()
    }
}