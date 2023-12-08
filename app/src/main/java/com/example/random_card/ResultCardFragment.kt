package com.example.random_card

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.random_card.databinding.FragmentResultCardBinding
import com.example.random_card.databinding.FragmentSelectCardBinding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter


import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultCardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */



class ResultCardFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var card : Int? = null

    private lateinit var binding: FragmentResultCardBinding
    private lateinit var viewKonfetti: KonfettiView

    private val viewModel: CardViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(CardViewModel::class.java)
        viewModel.card.observe(this, Observer<Int>{
                cards -> card = cards
        })
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResultCardBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        var input = requireArguments().getString("people")
        card = requireArguments().getInt("card")


        when(input){
            "2" -> {
                when(card) {
                    -1 -> {
                        binding.card.setImageResource(R.drawable.green_card)
                        viewModel.cardNumber(-1)
                    }
                    -2 -> {
                        binding.card.setImageResource(R.drawable.black_card)
                        viewModel.cardNumber(-2)
                    }
                }
            }
            "3" -> {
                when(card) {
                    10 -> {
                        binding.card.setImageResource(R.drawable.blue_card)
                        viewModel.cardNumber(10)
                    }
                    11 -> {
                        binding.card.setImageResource(R.drawable.green_card)
                        viewModel.cardNumber(11)
                    }
                    12 -> {
                        binding.card.setImageResource(R.drawable.black_card)
                        viewModel.cardNumber(12)
                    }
                }
            }
            "4" -> {
                when(card) {
                    1 -> {
                        binding.card.setImageResource(R.drawable.red_card)
                        viewModel.cardNumber(1)
                    }
                    2 -> {
                        binding.card.setImageResource(R.drawable.blue_card)
                        viewModel.cardNumber(2)
                    }
                    3 -> {
                        binding.card.setImageResource(R.drawable.green_card)
                        viewModel.cardNumber(3)
                    }

                    4 -> {
                        binding.card.setImageResource(R.drawable.black_card)
                        viewModel.cardNumber(4)
                    }
                }
            }
        }


        binding.menuBtn.setOnClickListener { view:View ->navigate(view, 1) }
        binding.retryBtn.setOnClickListener { view:View ->navigate(view, 2) }

        // 폭죽 애니메이션 넣기
        viewKonfetti = binding.konfettiView

        Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        val party = Party(20,speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        viewKonfetti.start(party)

        return binding.root
        // Inflate the layout for this fragment
    }


    private fun navigate(view:View, btn:Int){
        when(btn) {
            1 -> view.findNavController().navigate(R.id.action_ResultCardFragment_to_MenuFragment)
            else -> view.findNavController().navigate(R.id.action_ResultCardFragment_to_SelectPeopleFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultCardBlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultCardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}