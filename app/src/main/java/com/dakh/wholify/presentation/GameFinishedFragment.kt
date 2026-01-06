package com.dakh.wholify.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dakh.wholify.R
import com.dakh.wholify.databinding.FragmentGameFinishedBinding
import com.dakh.wholify.domain.entity.GameResult
import kotlin.getValue

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private lateinit var result: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        result = args.gameResult
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        setupClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListeners() {

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun bindViews() {
        binding.gameResult = result
        with(binding) {

            emojiResult.setImageResource(getSmileResId())

//            tvRequiredAnswers.text = String.format(
//                requireContext().resources.getString(R.string.required_score),
//                result.gameSettings.minCountOfRightAnswers
//            )
//            tvScoreAnswers.text = String.format(
//                requireContext().resources.getString(R.string.score_answers),
//                result.countOfRightAnswers
//            )
//
//
//            tvRequiredPercentage.text = String.format(
//                requireContext().resources.getString(R.string.required_percentage),
//                result.gameSettings.minPercentOfRightAnswers
//            )

            tvScorePercentage.text = String.format(
                requireContext().resources.getString(R.string.score_percentage),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getSmileResId(): Int {
        return if (result.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    private fun retryGame() {
       findNavController().popBackStack()
    }

    private fun getPercentOfRightAnswers(): String {
        return ((result.countOfRightAnswers / result.countOfQuestions.toDouble()) * 100).toString()
    }
}
