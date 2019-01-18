package com.presentation.ui.animatedLike

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.model.DataRow

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */

class AnimatedViewModel : ViewModel() {

    val particlesCount = ObservableField(5)
    val startParticleMaxDistance = ObservableField(2)

}
