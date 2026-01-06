package com.dakh.wholify.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dakh.wholify.R
import com.dakh.wholify.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    val smileResId = if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
    imageView.setImageResource(smileResId)
}

@BindingAdapter("tvScoreAnswers")
fun bindScoreAnswers(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        score
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percentage
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text =
        String.format(
            textView.context.getString(R.string.score_percentage),
            getPercentOfRightAnswers(gameResult)
        )
    }

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
}