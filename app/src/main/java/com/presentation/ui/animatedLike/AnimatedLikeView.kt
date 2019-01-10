package com.presentation.ui.animatedLike

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.recycler.R


class AnimatedLikeView : View, ValueAnimator.AnimatorUpdateListener {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val startAnimator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f).also {
        it.duration = 500
        it.interpolator = BounceInterpolator()
    }

    private val backAnimator: ValueAnimator = ValueAnimator.ofFloat(1.0f, 0f).also {
        it.duration = 150
        it.interpolator = LinearInterpolator()
    }

    private val animationSet = android.animation.AnimatorSet().also {
        it.playSequentially(startAnimator, backAnimator)
    }

    private var sizeRatio = 1.0f

    private val heart = VectorDrawableCompat.create(resources, R.drawable.ic_heart, null).also {
        if (it != null) {
            sizeRatio = it.intrinsicWidth.toFloat() / it.intrinsicHeight.toFloat()
        }
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) = invalidate()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawHeart(canvas)
    }

    private fun drawHeart(canvas: Canvas?) {

        if (heart == null || canvas == null) {
            return
        }

        val heartWidth = Math.round(width * 0.4)
        val heartHeight = heartWidth / sizeRatio

        val left = ((width - heartWidth) / 2f).toInt()
        val top = ((height - heartHeight) / 2f).toInt()

        if (animationSet.isRunning) {
            val animatedValue =
                (animationSet.childAnimations.first { it.isRunning } as ValueAnimator).animatedValue as Float

            heart.setBounds(
                (left - heartWidth * animatedValue).toInt(),
                (top - heartHeight * animatedValue).toInt(),
                (left + heartWidth + heartWidth * animatedValue).toInt(),
                (top + heartHeight + heartHeight * animatedValue).toInt()
            )
            heart.draw(canvas)
            invalidate()
        } else {
            heart.setBounds(left, top, (left + heartWidth).toInt(), (top + heartHeight).toInt())
            heart.draw(canvas)
        }
    }

    fun doLike() {
        if (!animationSet.isRunning) {
            animationSet.start()
            invalidate()
        }
    }
}