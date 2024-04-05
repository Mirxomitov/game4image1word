package uz.gita.fourpiconewordkotlin.presentation.dialog

class MyDialogPresenter : MyDialogContract.Presenter {

    var view: MyDialogContract.View
    var model: MyDialogContract.Model

    constructor (view: MyDialogContract.View) {
        this.view = view
        this.model = MyDialogModel()
    }

    override fun showNextLevel() {
        view.showMainScreen()
    }
}