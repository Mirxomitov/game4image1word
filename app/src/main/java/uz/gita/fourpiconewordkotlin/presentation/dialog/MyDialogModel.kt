package uz.gita.fourpiconewordkotlin.presentation.dialog

import uz.gita.fourpiconewordkotlin.domain.AppController

class MyDialogModel : MyDialogContract.Model {
    private var controller = AppController.getInstance()
}