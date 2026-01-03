package com.dakh.wholify.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dakh.wholify.R
import com.dakh.wholify.databinding.FragmentGameBinding
import com.dakh.wholify.domain.entity.GameResult
import com.dakh.wholify.domain.entity.GameSettings
import com.dakh.wholify.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level: Level
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption1.setOnClickListener {
            launchGameFinishedFragment(
                GameResult(
                    winner = true,
                    countOfRightAnswers = 14,
                    countOfQuestions = 29,
                    gameSettings = GameSettings(
                        maxSumValue = 56,
                        minCountOfRightAnswers = 34,
                        minPercentOfRightAnswers = 80,
                        gameTimeInSeconds = 60
                    )
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        level = requireArguments().getParcelable(KEY_LEVEL, Level::class.java)
            ?: throw IllegalArgumentException("Level argument is missing")
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_container,
                GameFinishedFragment.getInstance(gameResult)
            )
            .addToBackStack(null)
            .commit()
    }

    companion object {

        const val NAME = "GameFragment"

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}
