package com.example.kotlinfragment

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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OptionDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OptionDialogFragment : DialogFragment(), View.OnClickListener {
    private lateinit var btnPilih : Button
    private lateinit var btnKeluar : Button
    private lateinit var rg_option : RadioGroup
    private lateinit var rb_Saf : RadioButton
    private lateinit var rb_mou : RadioButton
    private lateinit var rb_lvg : RadioButton
    private lateinit var rb_moyes : RadioButton

    private var optionDialogListener : OnOptionDialogListener? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OptionDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OptionDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPilih = view.findViewById(R.id.btn_choose)
        btnKeluar = view.findViewById(R.id.btn_close)
        rg_option = view.findViewById(R.id.rg_opsi)
        rb_Saf = view.findViewById(R.id.rb_saf)
        rb_mou = view.findViewById(R.id.rb_mou)
        rb_lvg = view.findViewById(R.id.rb_lvg)
        rb_moyes = view.findViewById(R.id.rb_moyes)

        btnPilih.setOnClickListener(this)
        btnKeluar.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        //TODO("Not yet implemented")
        when (v.id) {
            R.id.btn_close -> dialog?.cancel()

            R.id.btn_choose -> {
                val checkedRadioButtonId = rg_option.checkedRadioButtonId
                if (checkedRadioButtonId != -1) {
                    var coach: String? = null

                    when (checkedRadioButtonId) {
                        R.id.rb_saf -> coach = rb_Saf.text.toString().trim()

                        R.id.rb_mou -> coach = rb_mou.text.toString().trim()

                        R.id.rb_lvg -> coach = rb_lvg.text.toString().trim()

                        R.id.rb_moyes -> coach = rb_moyes.text.toString().trim()
                    }

                    if (optionDialogListener != null) {
                        optionDialogListener?.onOptionChosen(coach)
                    }
                    dialog?.dismiss()
                }
            }
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(coach: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment

        if(fragment is DetailCategoryFragment) {
            val detailCategoryFragment = fragment
            this.optionDialogListener = detailCategoryFragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }
}
