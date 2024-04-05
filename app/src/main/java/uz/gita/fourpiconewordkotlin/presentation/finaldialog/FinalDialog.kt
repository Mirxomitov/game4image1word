package uz.gita.fourpiconewordkotlin.presentation.finaldialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import uz.gita.fourpiconewordkotlin.R
import uz.gita.fourpiconewordkotlin.domain.AppController

class FinalDialog : DialogFragment() {
    private val appController = AppController.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view: View = inflater.inflate(R.layout.final_dialog, container, false)
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.window?.setLayout(
            resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<AppCompatButton>(R.id.btnClearResults).setOnClickListener {
            listener?.invoke()
            dialog?.dismiss()
        }
    }

    private var listener: (() -> Unit)? = null
    fun setListener(block: () -> Unit) {
        listener = block
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//
//        return view
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NO_TITLE, R.style.Theme_Game2048v1)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
//        params.width = LayoutParams.MATCH_PARENT
//        params.height = LayoutParams.WRAP_CONTENT
//        dialog!!.window!!.attributes = params as LayoutParams
//    }
}