package uz.gita.fourpiconewordkotlin.presentation.main

import android.util.Log

class MainPresenter(private var view: MainContract.View) : MainContract.Presenter {

    private var model: MainContract.Model = MainModel()
    private var userAnswers = mutableListOf<Int>()
    private var helperAnswers = mutableListOf<Int>() // help orqali olingan javoblarni o'chirmaslik uchun

    init {
        newGame()
    }

    override fun onClickVariantButton(index: Int, letter: String) {
        val answerIndex = userAnswers.indexOf(-1)
        if (answerIndex != -1) {
            userAnswers[answerIndex] = index
            view.invisibleVariant(index)

            view.showVariantOnAnswer(answerIndex, letter)

            // TODO() LAST LEVEL ERRORS OCCURRED
            if (isWin()) {
                if (model.lastLevel()) {
                    view.openFinalDialog()
                } else {
                    view.openLevelDialog(true)
                }
            }
        }
    }


    override fun onClickAnswerButton(index: Int) {
        if (userAnswers[index] == -1 || index in helperAnswers) return
        view.removeFromAnswer(index)
        view.addToVariant(userAnswers[index])
        userAnswers[index] = -1
    }

    override fun onClickYes() {

        model.convertCoinsToHelper()
        view.setCoins(model.getCoins())

        return onClickHelp(true)
    }

    override fun onClickClearButton() {
        for (i in 0..<userAnswers.size) {
            onClickAnswerButton(i)
        }
        removeUserAnswersOnClickClear()
    }

    private fun removeUserAnswersOnClickClear() {
        for (i in 0..<userAnswers.size) {
            if (i !in helperAnswers) {
                userAnswers[i] = -1
            }
        }
    }

    override fun saveLevel() {
        model.saveLevel()
    }

    override fun saveCoins() {
        model.saveCoins()
    }

    override fun saveHelperCount() {
        model.saveHelperCount()
    }

    override fun onClickHelp(isSecondTime: Boolean) {
        val index = userAnswers.indexOf(-1)

        if (index == -1) {
            for (i in 0 until model.getCurrentQuestion().answer.length) {
                val userLetter = model.getCurrentQuestion().variant[userAnswers[i]]
                if (userLetter != model.getCurrentQuestion().answer[i]) {
                    if (isSecondTime) {
                        view.showHelperDialog("${i + 1} - dagi $userLetter harfni ${model.getCurrentQuestion().answer[i]} ga almashtiring")
                        model.decreaseHelperCount()
                    } else if (model.getHelperCount() <= 0) {
                        if (model.canConvertCoinsToHelper()) {
                            view.showConvertDialog()
                        } else {
                            view.openNoEnoughCoinsDialog()
                        }
                    }
                    break
                }
            }
            return
        }


        Log.d("TTT", "onClickHelp: index = $index")
        Log.d("TTT", "onClickHelp: userAnswers = $userAnswers")

        if (model.getHelperCount() <= 0) {
            if (model.canConvertCoinsToHelper()) {
                view.showConvertDialog()
            } else {
                view.openNoEnoughCoinsDialog()
            }
            return
        }

        val answerHelp = model.getCurrentQuestion().answer[index].toString()
        val answerHelpIndex = model.getCurrentQuestion().variant.indexOf(answerHelp)

        view.showVariantOnAnswer(index, answerHelp)
        view.showHelperAnswer(index, answerHelp)
        helperAnswers.add(index)

        userAnswers[index] = answerHelpIndex
        view.invisibleVariant(answerHelpIndex)

        model.decreaseHelperCount()
        if (index == userAnswers.size - 1) {
            if (isWin()) {
                if (model.lastLevel()) {
                    view.openFinalDialog()
                } else {
                    view.openLevelDialog(true)
                }
            }
        }
    }

    private fun newGame() {
        view.loadButtons(model.getCurrentQuestion().variant)
        view.loadImages(model.getCurrentQuestion().question)
        view.visibleAllVariants()
        view.fixAnswerLength(model.getCurrentQuestion().answer.length)
        view.setLevel(model.getLevel())
        view.setCoins(model.getCoins())
        clearUserAnswers()
        helperAnswers.clear()   
        fillAnswerList()
        view.clearAnswersText()
        view.clearHelpersBackground()
    }

    override fun onClickNext() {
        if (!model.lastLevel()) model.increaseLevel()
        model.increaseCoins()
        newGame()
    }

    override fun onClickHome() {
        if (!model.lastLevel()) {
            model.increaseLevel()
        }
        model.increaseCoins()
        view.openMenuScreen()
    }

    override fun saveHelperAnswerToDeviceMemory() {
        model.saveHelperAnswerToDeviceMemory(helperAnswers)
    }

    override fun setHelperAnswersToView() {
        helperAnswers = model.getHelperAnswers()
        if (helperAnswers.size == 0) return;

        for (i in helperAnswers) {
            view.showHelperAnswer(i, model.getCurrentQuestion().answer[i].toString())
        }
    }

    private fun clearUserAnswers() {
        userAnswers.clear()
    }

    private fun isWin(): Boolean {
        if (!isFull()) return false

        val correctAnswer = model.getCurrentQuestion().answer
        val variant = model.getCurrentQuestion().variant

        for (i in userAnswers.indices) {
            if (variant[userAnswers[i]] != correctAnswer[i]) return false
        }

        return true
    }

    private fun isFull(): Boolean = userAnswers.indexOf(-1) == -1

    private fun fillAnswerList() {
        for (i in 0 until model.getCurrentQuestion().answer.length) {
            userAnswers += -1
        }
    }

    override fun clearResult() {
        model.clearResult()
        newGame()
    }
}

fun logger(msg: String) {
    Log.d("TTT", msg)
}
