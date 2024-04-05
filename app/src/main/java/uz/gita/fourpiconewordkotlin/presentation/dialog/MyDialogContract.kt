package uz.gita.fourpiconewordkotlin.presentation.dialog

interface MyDialogContract {
    interface View {
        fun showMainScreen()
    }

    interface Model

    interface Presenter {
        fun showNextLevel()
    }
}