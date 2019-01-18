package com.presentation.ui.animatedLike

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.R
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class AnimatedLikeView : View, ValueAnimator.AnimatorUpdateListener {

    private val paints = listOf(
        Paint().also {
            it.color = resources.getColor(R.color.like_animation_green, null)
            it.style = Paint.Style.FILL
        },
        Paint().also {
            it.color = resources.getColor(R.color.like_animation_blue, null)
            it.style = Paint.Style.FILL
        },
        Paint().also {
            it.color = resources.getColor(R.color.like_animation_red, null)
            it.style = Paint.Style.FILL
        }
    )

    private val paths = listOf(
        Path().also {
            it.addCircle(0.5f, 0.5f, 0.5f, Path.Direction.CW)
        },
        Path().also {
            it.moveTo(0f, .609f)
            it.lineTo(.344f, .670f)
            it.lineTo(.492f, 1f)
            it.lineTo(.653f, .677f)
            it.lineTo(1f, .632f)
            it.lineTo(.755f, .372f)
            it.lineTo(.822f, .014f)
            it.lineTo(.509f, .177f)
            it.lineTo(.204f, 0f)
            it.lineTo(.255f, .361f)
            it.lineTo(0f, .609f)
            it.close()
        })


    constructor (context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val startAnimator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f).also {
        it.duration = 500
        it.interpolator = LinearOutSlowInInterpolator()
    }

    private val backAnimator: ValueAnimator = ValueAnimator.ofFloat(1.0f, 0f).also {
        it.duration = 150
        it.interpolator = LinearInterpolator()
    }

    private val particleDistanceAnimator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f).also {
        it.duration = 750
        it.interpolator = LinearOutSlowInInterpolator()
    }

    private val particleSizeAnimator: ValueAnimator = ValueAnimator.ofFloat(40f, 60f, 0f).also {
        it.duration = 750
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

    private lateinit var particles: List<Particle>

    override fun onAnimationUpdate(animation: ValueAnimator?) = invalidate()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        drawHeart(canvas)
        drawParticles(canvas)
    }

    private fun drawHeart(canvas: Canvas) {
        if (heart == null) {
            throw IllegalStateException("Heart drawable must not be null.")
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
        } else {
            heart.setBounds(left, top, (left + heartWidth).toInt(), (top + heartHeight).toInt())
        }
        heart.draw(canvas)
    }

    private val maxMoveDistance = 200.0

    private val pathBuffer = Path()
    private val pathBuilder = Path()

    private fun drawParticles(canvas: Canvas) {

        pathBuilder.reset()

        if (particleDistanceAnimator.isRunning) {

            val distance = maxMoveDistance * particleDistanceAnimator.animatedValue as Float
            val scale = particleSizeAnimator.animatedValue as Float

            val scaleMatrix = Matrix().also {
                it.setScale(scale, scale)
            }
            for (particle in particles) {

                val x = width / 2 + cos(particle.angle.toRad()) * (particle.dist + distance)
                val y = height / 2 + sin(particle.angle.toRad()) * (particle.dist + distance)

                canvas.save()
                //scale
                particle.path.transform(scaleMatrix, pathBuffer)
                //draw
                canvas.translate(x.toFloat(), y.toFloat())

                //move
                canvas.drawPath(pathBuffer, particle.paint)

                canvas.restore()
            }
            invalidate()
        }
    }

    fun doLike() {
        if (!animationSet.isRunning) {
            particles = generateParticles()
            animationSet.start()
            particleDistanceAnimator.start()
            particleSizeAnimator.start()
            invalidate()
        }
    }

    var particlesCount = 50
    var startDistanceMax : Float= 200.0f

    private fun generateParticles() = 0.until(particlesCount).map {
        Particle(
            angle = Random.nextInt(360),
            dist = Random.nextFloat() * startDistanceMax,
            path = paths.random(),
            paint = paints.random()
        )
    }

    fun stop() {
        if (!animationSet.isRunning) {
            animationSet.cancel()
            particleDistanceAnimator.cancel()
            particleSizeAnimator.cancel()
            invalidate()
        }
    }

    private class Particle(val angle: Int, val dist: Float, val path: Path, val paint: Paint)

    private fun Int.toRad() = Math.toRadians(this.toDouble())
}