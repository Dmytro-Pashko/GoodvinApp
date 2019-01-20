package com.presentation.ui.animatedLike

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */

class AnimatedViewModel : ViewModel() {

    val heartSize = ObservableField<Float>(0.4f)
    val heartAnimationDuration = ObservableField<Long>(500)
    val heartScaleFactor = ObservableField<Float>(0.2f)

    val particlesCount = ObservableField(50)
    val particleStartDist = ObservableField(200)
    val particleMovementDist = ObservableField(200)
    val particleMovementDuration = ObservableField(500)
    val particleScaleFactor = ObservableField(1f)
}
