package com.presentation.ui.animatedLike

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
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

    private val startAnimator: ValueAnimator = ValueAnimator.ofFloat(0f, 0.4f).also {
        it.duration = 500
        it.interpolator = LinearOutSlowInInterpolator()
    }

    private val backAnimator: ValueAnimator = ValueAnimator.ofFloat(0.4f, 0f).also {
        it.duration = 150
        it.interpolator = LinearInterpolator()
    }

    private val heartAnimatorSet = android.animation.AnimatorSet().also {
        it.playSequentially(startAnimator, backAnimator)
    }

    private val particleSizeAnimator: ValueAnimator = ValueAnimator.ofFloat(40f, 60f, 0f).also {
        it.duration = 750
        it.interpolator = LinearInterpolator()
    }

    private val particleDistanceAnimator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f).also {
        it.duration = 750
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

    private val startHeartSize = 0.4f
    private val heart = R.drawable.ic_heart.asVector()

    private var heartAspectRatio = calculateHeartRatio()

    private fun drawHeart(canvas: Canvas) {

        val heartWidth: Int
        val heartHeight: Int

        val animatedValue: Float = if (heartAnimatorSet.isRunning)
            (heartAnimatorSet.childAnimations.first { it.isRunning } as ValueAnimator).animatedValue as Float
        else 0f

        if (width > height) {
            heartHeight = (height * (startHeartSize + animatedValue)).toInt()
            heartWidth = (heartHeight / heartAspectRatio).toInt()
        } else {
            heartWidth = (width * (startHeartSize + animatedValue)).toInt()
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

    var particlesCount = 50
    var startDistanceMax = 200.0f
    private val maxMoveDistance = 200.0

    private lateinit var particles: List<Particle>

    private fun generateParticles() = 0.until(particlesCount).map {
        Particle(
            angle = Random.nextInt(360),
            dist = Random.nextFloat() * startDistanceMax,
            drawable = particlesDrawable.random(),
            color = colors.random()
        )
    }

    private fun drawParticles(canvas: Canvas) {

        if (particleDistanceAnimator.isRunning) {

            val distance = maxMoveDistance * particleDistanceAnimator.animatedValue as Float
            val scale = particleSizeAnimator.animatedValue as Float

            for (particle in particles) {

                particle.drawable.setColorFilter(particle.color, PorterDuff.Mode.SRC_IN)

                val x = width / 2 + cos(particle.angle.toRad()) * (particle.dist + distance)
                val y = height / 2 + sin(particle.angle.toRad()) * (particle.dist + distance)

                particle.drawable.setBounds(x.toInt(), y.toInt(), (x + 50.0).toInt(), (y + 50.0).toInt())
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

    fun stop() {
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