package com.example.myapplication.core.extensions

import android.app.Activity
import com.example.myapplication.R


fun Activity.openWithAnimation() {
    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
}

fun Activity.closeWithAnimation() {
    overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
}

fun Activity.openWithSlideUpAnimation() {
    overridePendingTransition(R.anim.slide_up, R.anim.stay)
}

fun Activity.closeWithSlideDownAnimation() {
    overridePendingTransition(R.anim.stay, R.anim.slide_down)
}

