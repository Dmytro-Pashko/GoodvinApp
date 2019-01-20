package com.presentation.ui.animatedLike

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.FloatRange
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.R
import com.presentation.utils.asResourceColor
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class AnimatedLikeView : View, ValueAnimator.AnimatorUpdateListener {

    constructor (context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val colors = listOf(
        R.color.like_animation_blue.asResourceColor(),
        R.color.like_animation_green.asResourceColor(),
        R.color.like_animation_red.asResourceColor()
    )

    private val particlesDrawable: List<VectorDrawableCompat> = listOf(
        R.drawable.animation_particle_star.asVector(),
        R.drawable.animation_particle_circle.asVector(),
        R.drawable.animation_particle_square.asVector()
    )

    private val startAnimator: ValueAnimator = ValueAnimator.ofFloat().also {
        it.interpolator = LinearOutSlowInInterpolator()
    }

    private val backAnimator: ValueAnimator = ValueAnimator.ofFloat().also {
        it.interpolator = LinearInterpolator()
    }

    private val heartAnimatorSet = android.animation.AnimatorSet().also {
        it.playSequentially(startAnimator, backAnimator)
    }

    private val particleSizeAnimator: ValueAnimator = ValueAnimator.ofFloat().also {
        it.interpolator = LinearInterpolator()
    }

    private val particleDistanceAnimator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f).also {
        it.interpolator = LinearOutSlowInInterpolator()
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) = invalidate()

    private val isAnimationRunning: Boolean
        get() = heartAnimatorSet.isRunning || particleSizeAnimator.isRunning || particleDistanceAnimator.isRunning

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        drawHeart(canvas)
        drawParticles(canvas)

        if (isAnimationRunning) {
            invalidate()
        }
    }

    var heartSize = 0.4f
        set(@FloatRange(from = 0.0, to = 1.0) value) {
            stop()
            field = value
            invalidate()
        }
    var heartAnimationDuration = 750L
        set(value) {
            stop()
            field = value
            startAnimator.duration = (value * 0.75).toLong()
            backAnimator.duration = (value * 0.25).toLong()
            invalidate()
        }
    var heartScaleFactor = 0.4f
        set(@FloatRange(from = 0.0, to = 1.0) value) {
            stop()
            field = value
            startAnimator.setFloatValues(0f, value)
            backAnimator.setFloatValues(value, 0f)
            invalidate()
        }

    private val heart = R.drawable.ic_heart.asVector()
    private var heartAspectRatio = calculateHeartRatio()

    private fun drawHeart(canvas: Canvas) {

        val heartWidth: Int
        val heartHeight: Int

        val animatedValue: Float = if (heartAnimatorSet.isRunning)
            (heartAnimatorSet.childAnimations.first { it.isRunning } as ValueAnimator).animatedValue as Float
        else 0f

        if (width > height) {
            heartHeight = (height * (heartSize + animatedValue)).toInt()
            heartWidth = (heartHeight / heartAspectRatio).toInt()
        } else {
            heartWidth = (width * (heartSize + animatedValue)).toInt()
            heartHeight = (heartWidth / heartAspectRatio).toInt()
        }

        val left = ((width - heartWidth) / 2f).toInt()
        val top = ((height - heartHeight) / 2f).toInt()

        heart.setBounds(left, top, (left + heartWidth), (top + heartHeight))

        heart.draw(canvas)
    }

    private fun calculateHeartRatio(): Float {

        val w = heart.intrinsicWidth.toFloat()
        val h = heart.intrinsicHeight.toFloat()
        return if (w < h) {
            w / h
        } else {
            h / w
        }
    }

    private lateinit var particles: List<Particle>

    var particlesCount = 50
        set(value) {
            stop()
            field = value
            invalidate()
        }
    var particleStartDist = 200.0f
        set(value) {
            stop()
            field = value
            invalidate()
        }

    var particleMovementDist = 200.0f
        set(value) {
            stop()
            field = value
            invalidate()
        }

    var particleMovementDuration = 750L
        set(value) {
            stop()
            field = value
            particleDistanceAnimator.duration = value
            particleSizeAnimator.duration = value
            invalidate()
        }

    var particleScaleFactor = 2.0f
        set(value) {
            stop()
            field = value
            particleSizeAnimator.setFloatValues(0.5f, value, -1f)
            invalidate()
        }

    private fun generateParticles() = 0.until(particlesCount).map {
        Particle(
            angle = Random.nextInt(360),
            dist = Random.nextFloat() * particleStartDist,
            drawable = particlesDrawable.random(),
            color = colors.random()
        )
    }

    private fun drawParticles(canvas: Canvas) {

        if (particleDistanceAnimator.isRunning) {

            val distance = particleMovementDist * particleDistanceAnimator.animatedValue as Float
            val scale = particleSizeAnimator.animatedValue as Float


            for (particle in particles) {

                particle.drawable.setColorFilter(particle.color, PorterDuff.Mode.SRC_IN)

                val x: Int = (width / 2 + cos(particle.angle.toRad()) * (particle.dist + distance)).toInt()
                val y: Int = (height / 2 + sin(particle.angle.toRad()) * (particle.dist + distance)).toInt()

                val w: Int
                particle.drawable.intrinsicWidth.also {
                    w = it + (it * scale).toInt()
                }
                val h: Int
                particle.drawable.intrinsicHeight.also {
                    h = it + (it * scale).toInt()
                }

                particle.drawable.setBounds(x - w / 2, y - h / 2, x + w, y + h)
                particle.drawable.draw(canvas)

            }
        }
    }

    fun start() {
        if (!isAnimationRunning) {
            particles = generateParticles()
            heartAnimatorSet.start()
            particleDistanceAnimator.start()
            particleSizeAnimator.start()
            invalidate()
        }
    }

    private fun stop() {
        if (isAnimationRunning) {
            heartAnimatorSet.cancel()
            particleDistanceAnimator.cancel()
            particleSizeAnimator.cancel()
            invalidate()
        }
    }

    private class Particle(val angle: Int, val dist: Float, val drawable: VectorDrawableCompat, val color: Int)

    private fun Int.toRad() = Math.toRadians(this.toDouble())

    private fun Int.asVector() = VectorDrawableCompat.create(resources, this, null)
        ?: throw java.lang.IllegalStateException("Incorrect resource.")
}
