package com.example.random_card

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.text.isDigitsOnly
import androidx.navigation.findNavController
import com.example.random_card.databinding.FragmentCalculateBinding
import com.example.random_card.databinding.FragmentResultMoneyBinding
import com.example.random_card.databinding.FragmentSelectCardBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalculateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private lateinit var binding: FragmentCalculateBinding

class CalculateFragment : Fragment() {
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
        binding = FragmentCalculateBinding.inflate(inflater, container, false)
        binding.selectCalBtn.setOnClickListener { view:View ->navigate(view) }

        return binding.root
    }

    private fun navigate(view:View){

        if (!binding.money.text.trim().isNullOrEmpty() && !binding.people.text.trim().isNullOrEmpty()) {

            if (binding.money.text.isDigitsOnly()
                && binding.people.text.isDigitsOnly()
                && binding.people.text.toString().toInt() > 0) {
                    var money: Int = binding.money.text.toString().toInt()
                    var people: Int = binding.people.text.toString().toInt()
                    var mean = money / people

                    val bundle = bundleOf("mean" to mean.toString())

                    view.findNavController()
                        .navigate(R.id.action_CalculateFragment_to_ResultMoneyFragment, bundle)
            }
            else{
                Toast.makeText(this.context, "잘못된 입력입니다. 다시 입력해주세요.", Toast.LENGTH_SHORT, ).show()

            }
            // 네비게이션을 위한 기존 로직
        } else {
            Toast.makeText(this.context, "잘못된 입력입니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show()

            // 두 필드 중 하나라도 비어 있는 경우 처리
            // 오류 메시지를 표시하거나 적절한 조치를 취할 수 있습니다.
        }

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalculateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalculateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}