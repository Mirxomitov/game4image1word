package uz.gita.fourpiconewordkotlin.presentation.menu

import uz.gita.fourpiconewordkotlin.domain.AppController

class MenuModel : MenuContract.Model {

    private var controller = AppController.getInstance()

    override fun clearResult() {
        controller.clearResult()
    }

    override fun getImages() : IntArray {
       return controller.getCurrentQuestion().question
    }

    override fun getLevel(): Int {
        return controller.getLevel()
    }

    override fun getCoins(): Int {
        return controller.getCoins()
    }
}