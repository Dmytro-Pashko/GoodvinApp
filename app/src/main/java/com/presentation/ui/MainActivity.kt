package com.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.presentation.ui.animatedLike.AnimatedLikeFragment
import com.R
import com.presentation.ui.recycler.RecyclerFragment

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                replace(R.id.container, AnimatedLikeFragment.newInstance())
            }
        }
    }

}
