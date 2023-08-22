package com.desirecodes.blurkit

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.desirecodes.blurlibrary.BlurLayout

class MainActivity : AppCompatActivity() {
    private var blurLayout: BlurLayout? = null
    private var movement = 150f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        blurLayout = findViewById<BlurLayout>(R.id.blurLayout)
        blurLayout?.animate()?.translationY(movement)?.setDuration(1500)
            ?.setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    movement = if (movement > 0) (-150).toFloat() else 150.toFloat()
                    blurLayout?.animate()?.translationY(movement)?.setDuration(1500)
                        ?.setListener(this)
                        ?.start()
                }
            })?.start()
    }

    override fun onStart() {
        super.onStart()
        blurLayout?.startBlur()
        blurLayout?.lockView()
    }

    override fun onStop() {
        super.onStop()
        blurLayout?.pauseBlur()
    }
}