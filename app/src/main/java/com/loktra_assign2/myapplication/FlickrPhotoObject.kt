package com.loktra_assign2.myapplication

import android.animation.AnimatorSet

/**
 * Created by user on 4/30/2018.
 */
data class FlickrPhotoObject(var url:String, var title:String, var rotated:Boolean,
                             var animatorSet: AnimatorSet?)
