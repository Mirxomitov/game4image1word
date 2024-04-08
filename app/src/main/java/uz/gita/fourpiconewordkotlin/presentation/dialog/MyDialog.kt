package uz.gita.fourpiconewordkotlin.presentation.dialog

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import uz.gita.fourpiconewordkotlin.R

class MyDialog : DialogFragment(), MyDialogContract.View {

    private lateinit var listener: Unit
    private lateinit var presenter: MyDialogContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setLayout(resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
        return inflater.inflate(R.layout.my_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MyDialogPresenter(this)

        view.findViewById<AppCompatButton>(R.id.btnNext).setOnClickListener {
            listener
        }
    }

    override fun showMainScreen() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
    }
}