package com.example.kotlinfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailCategoryFragment : Fragment(), View.OnClickListener {
    lateinit var tvNamaKategori : TextView
    lateinit var tvDeskripsiKategori : TextView
    lateinit var btnProfil : Button
    lateinit var btnTampilDialog : Button

    private var param1: String? = null
    private var param2: String? = null

    var description: String? = null

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
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"

        fun newInstance(param1: String, param2: String) =
            DetailCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvNamaKategori = view.findViewById(R.id.tv_nama_kategori)
        tvDeskripsiKategori = view.findViewById(R.id.tv_deskripsi_kategori)
        btnProfil = view.findViewById(R.id.btn_profil)
        btnTampilDialog = view.findViewById(R.id.btn_tampil_dialog)

        btnProfil.setOnClickListener(this)
        btnTampilDialog.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        //TODO("Not yet implemented")
        when (v.id) {
            R.id.btn_profil -> {
                val mIntent = Intent(activity, ProfilActivity::class.java)
                startActivity(mIntent)
            }

            R.id.btn_tampil_dialog -> {
                val mOptionDialogFragment = OptionDialogFragment()

                val mFragmentManager = childFragmentManager
                mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment::class.java.simpleName)
                
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }

        if (arguments != null) {
            val namaKategori = arguments?.getString(EXTRA_NAME)
            tvNamaKategori.text = namaKategori
            tvDeskripsiKategori.text = description
        }
    }

    internal var optionDialogListener : OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(coach: String?) {
            //TODO("Not yet implemented")
            Toast.makeText(activity, coach, Toast.LENGTH_SHORT).show()
        }
    }
}