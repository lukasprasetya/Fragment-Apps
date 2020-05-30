package com.example.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_option_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class OptionDialogFragment : DialogFragment(), View.OnClickListener {

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbSaf: RadioButton
    private lateinit var rbMou: RadioButton
    private lateinit var rblvg: RadioButton
    private lateinit var rbMoyes: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null
    var coach: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_Choose)
        btnChoose.setOnClickListener(this)
        btnClose = view.findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        rgOptions = view.findViewById(R.id.rg_option)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbMou = view.findViewById(R.id.rb_mou)
        rblvg = view.findViewById(R.id.rb_lvg)
        rbMoyes = view.findViewById(R.id.rb_moyes)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment) {
            val detailCategoryFragment = fragment
            this.optionDialogListener = detailCategoryFragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_close -> dialog?.cancel()

            R.id.btn_Choose -> {
                val checkedRadioButtonId = rg_option.checkedRadioButtonId
                if (checkedRadioButtonId != -1)

                when (checkedRadioButtonId) {
                    R.id.rb_saf -> coach = rbSaf.text.toString().trim()

                    R.id.rb_mou -> coach = rbMou.text.toString().trim()

                    R.id.rb_lvg -> coach = rblvg.text.toString().trim()

                    R.id.rb_moyes -> coach = rbMoyes.text.toString().trim()
                }

                if (optionDialogListener != null) {
                    optionDialogListener?.onOptionChosen(coach)
                }
                dialog?.dismiss()
            }
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}

