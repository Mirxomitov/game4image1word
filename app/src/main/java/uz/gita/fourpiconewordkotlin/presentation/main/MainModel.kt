package uz.gita.fourpiconewordkotlin.presentation.main

import uz.gita.fourpiconewordkotlin.data.model.QuestionData
import uz.gita.fourpiconewordkotlin.domain.AppController

class MainModel : MainContract.Model {
    private val appController: AppController = AppController.getInstance()

    override fun getCurrentQuestion(): QuestionData {
        return appController.getCurrentQuestion()
    }

    override fun increaseLevel() {
        appController.increaseLevel()
    }

    override fun isFinish(): Boolean {
        return appController.isFinish()
    }

    override fun getLevel(): Int {
        return appController.getLevel()
    }

    override fun saveLevel() {
        appController.saveLevel()
    }

    override fun saveCoins() {
        appController.saveCoins()
    }

    override fun saveHelperCount() {
        appController.saveHelperCount()
    }

    override fun decreaseHelperCount() {
        appController.decreaseHelperCount()
    }

    override fun increaseHelperCount() {
        appController.increaseHelperCount()
    }

    override fun increaseCoins() {
        appController.increaseCoins()
    }

    override fun getHelperCount(): Int {
        return appController.getHelperCount()
    }

    override fun getCoins(): Int {
        return appController.getCoins()
    }

    override fun canConvertCoinsToHelper() : Boolean {
       return appController.canChangeCoinsToHelper()
    }

    override fun convertCoinsToHelper() {
        appController.changeCoinsToHelper()
    }

    override fun saveHelperAnswerToDeviceMemory(helperAnswers : MutableList<Int>) {
        appController.saveHelperAnswerToDeviceMemory(helperAnswers)
    }

    override fun getHelperAnswers() : MutableList<Int> {
        return appController.getHelperAnswers()
    }

    override fun clearResult() {
        appController.clearResult()
    }

    override fun lastLevel(): Boolean {
        return appController.isLastLevel()
    }
}
