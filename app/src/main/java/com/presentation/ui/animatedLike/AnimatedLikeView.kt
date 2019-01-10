package com.presentation.ui.animatedLike

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.graphics.scale
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
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

    private val particleAnimator = ValueAnimator.ofFloat(0.5f, 1f, 0f).also {
        it.duration = 750
        it.interpolator = LinearOutSlowInInterpolator()
    }

    private val animationSet = android.animation.AnimatorSet().also {
        it.playSequentially(startAnimator, backAnimator)
    }

    private var ratio = 1.0f

    private val heartDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_heart, null).also {
        it.
        if (it != null) {
            ratio = it.intrinsicWidth.toFloat() / it.intrinsicHeight.toFloat()
        }
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) = invalidate()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawHeart(canvas)
    }

    private fun drawHeart(canvas: Canvas?) {
        val heartWidth = Math.round(width * 0.4)
        val heartHeight = heartWidth / ratio

        if (animationSet.isRunning) {
            val animatedValue =
                (animationSet.childAnimations.first { it.isRunning } as ValueAnimator).animatedValue as Float
            val animatedWidth = (animatedValue * heartWidth) + heartWidth
            val animatedHeight = (animatedValue * heartHeight) + heartHeight


//            canvas?.drawBitmap(
//                heartDrawable.scale(animatedWidth.toInt(), animatedHeight.toInt()),
//                (width - animatedWidth) / 2f, (height - animatedHeight) / 2f, null
//            )
//            invalidate()
        } else {

                heartDrawable?.draw(canvas)
//            canvas?.drawBitmap(
//                heartDrawable.scale(heartWidth.toInt(), heartHeight.toInt()),
//                (width - heartWidth) / 2f, (height - heartHeight) / 2f, null
//            )
        }
        invalidate()
    }

    fun doLike() {
        if (!animationSet.isRunning) {
            animationSet.start()
            invalidate()
        }
    }
}