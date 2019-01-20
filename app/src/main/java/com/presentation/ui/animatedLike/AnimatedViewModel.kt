package com.presentation.ui.animatedLike

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.model.DataRow

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */

class AnimatedViewModel : ViewModel() {

    val particlesCount = ObservableField(50)
    val particleStartDist = ObservableField(200)
    val particleMovementDist = ObservableField(200)

    val heartSize = ObservableField<Float>(0.4f)
    val heartAnimationDuration = ObservableField<Long>(500)
    val heartScaleFactor = ObservableField<Float>(0.2f)
}
