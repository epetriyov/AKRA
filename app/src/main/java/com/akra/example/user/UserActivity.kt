package com.akra.example.user

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.akra.example.R
import javax.inject.Inject

/**
 * Created by Евгений on 8/21/2017.
 */

class UserActivity : AppCompatActivity() {

    @Inject lateinit var modulesPresentationModel: UserPresentationModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

    }
}
