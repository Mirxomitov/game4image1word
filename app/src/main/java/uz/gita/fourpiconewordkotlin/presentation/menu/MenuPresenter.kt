package uz.gita.fourpiconewordkotlin.presentation.menu

class MenuPresenter(private val view: MenuContract.View) : MenuContract, MenuContract.Presenter {
    private val model: MenuContract.Model

    init {
        model = MenuModel()
        view.showImages(model.getImages())
        view.updateCoins(model.getCoins().toString())
        view.updateLevel("Bosqich: " + model.getLevel())
    }

    override fun clearResult() {
        model.clearResult()
    }

    override fun setImages() {
        view.showImages(model.getImages())
    }

    override fun setCoins() {
        view.updateCoins(model.getCoins().toString() + "  ")
    }

    override fun setLevel() {
        view.updateLevel("Bosqich: " + model.getLevel().toString())
    }
}