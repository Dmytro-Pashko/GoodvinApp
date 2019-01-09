package com.recycler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.transaction
import com.recycler.R
import com.recycler.ui.recycler.RecyclerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction{
                replace(R.id.container, RecyclerFragment.newInstance())
            }
        }
    }

}
