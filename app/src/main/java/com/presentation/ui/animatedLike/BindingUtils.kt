package com.presentation.ui.animatedLike

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.presentation.utils.dp2px
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

/**
 * Created by Dmytro Pashko on 1/18/2019.
 */

object BindingUtils {

    @JvmStatic
    @BindingAdapter("android:particleCount")
    fun particlesCount(view: AnimatedLikeView, value: ObservableField<Int>) {
        view.particlesCount = value.get() ?: 0
    }

    @JvmStatic
    @BindingAdapter("android:particleStartDist")
    fun startParticleMaxDist(view: AnimatedLikeView, value: ObservableField<Int>) {
        view.particleStartDist = (value.get() ?: 0).dp2px()
    }

    @JvmStatic
    @BindingAdapter("android:particleMovementDist")
    fun particleMovementDist(view: AnimatedLikeView, value: ObservableField<Int>) {
        view.particleMovementDist = (value.get() ?: 0).dp2px()
    }

    @JvmStatic
    @BindingAdapter("android:heartSize")
    fun heartSize(view: AnimatedLikeView, value: ObservableField<Float>) {
        view.heartSize = value.get() ?: 0.0f
    }

    @JvmStatic
    @BindingAdapter("android:heartAnimationDuration")
    fun heartAnimationDuration(view: AnimatedLikeView, value: ObservableField<Long>) {
        view.heartAnimationDuration = value.get() ?: 0
    }

    @JvmStatic
    @BindingAdapter("android:heartScaleFactor")
    fun heartScaleFactor(view: AnimatedLikeView, value: ObservableField<Float>) {
        view.heartScaleFactor = value.get() ?: 0.0f
    }

    @JvmStatic
    @BindingAdapter("android:particleMovementDuration")
    fun particleMovementDuration(view: AnimatedLikeView, value: ObservableField<Long>) {
        view.particleMovementDuration = value.get() ?: 0
    }

    @JvmStatic
    @BindingAdapter("android:particleScaleFactor")
    fun particleScaleFactor(view: AnimatedLikeView, value: ObservableField<Float>) {
        view.particleScaleFactor = value.get() ?: 0.0f
    }

    @JvmStatic
    @BindingAdapter("android:seekObserver")
    fun intSeekObserver(view: IndicatorSeekBar, observer: ObservableField<Int>) {
        view.onSeekChangeListener = object : OnSeekChangeListener {

            override fun onSeeking(seekParams: SeekParams?) {
                if (seekParams != null) {
                    observer.set(seekParams.progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {}

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {}

        }
    }

    @JvmStatic
    @BindingAdapter("android:seekObserver")
    fun floatSeekObserver(view: IndicatorSeekBar, observer: ObservableField<Float>) {
        view.onSeekChangeListener = object : OnSeekChangeListener {

            override fun onSeeking(seekParams: SeekParams?) {
                if (seekParams != null) {
                    observer.set(seekParams.progressFloat)
                }
            }

            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {}

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {}

        }
    }


    @JvmStatic
    @BindingAdapter("android:seekObserver")
    fun longSeekObserver(view: IndicatorSeekBar, observer: ObservableField<Long>) {
        view.onSeekChangeListener = object : OnSeekChangeListener {

            override fun onSeeking(seekParams: SeekParams?) {
                if (seekParams != null) {
                    observer.set(seekParams.progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {}

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {}

        }
    }
}