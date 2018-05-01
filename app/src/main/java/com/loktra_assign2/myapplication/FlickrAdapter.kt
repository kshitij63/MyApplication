package com.loktra_assign2.myapplication

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_photo_view.view.*


/**
 * Created by user on 4/30/2018.
 */
class FlickrAdapter(var con: Context, var list: ArrayList<FlickrPhotoObject>) : RecyclerView.Adapter<FlickrAdapter.FlickViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.single_photo_view, parent, false)
        var holder = FlickViewHolder(view)

        return holder

    }

    override fun getItemCount(): Int {
        return list.size
    }

    // @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: FlickViewHolder, position: Int) {


        var obj = list.get(position)
        if (obj.rotated == true)
            holder.photo.visibility = View.GONE
        else
            holder.photo.visibility = View.VISIBLE

        /*      var animator=ObjectAnimator.ofFloat(holder.photo,View.ROTATION_Y,180f)
              var animator2=ObjectAnimator.ofFloat(holder.photo,View.ALPHA,1f,0f)
              var animatorSet=AnimatorSet()
              animatorSet.playTogether(animator,animator2)
              animatorSet.setDuration(1000)
      */

        Picasso.get().load(obj.url).placeholder(R.drawable.placeholder).into(holder.photo)
        holder.title.setText(obj.title)

        holder.itemView.setOnClickListener {
            if (obj.rotated) {
                obj.rotated = false


                holder.photo.visibility = View.VISIBLE

            } else {
                obj.rotated = true
                /*        var animator = ObjectAnimator.ofFloat(holder.photo, View.ROTATION_Y, 180f)
                        var animator2 = ObjectAnimator.ofFloat(holder.photo, View.ALPHA, 1f, 0f)
                        var animatorSet = AnimatorSet()
                        if (obj.animatorSet == null)
                            obj.animatorSet = animatorSet
                        obj.animatorSet!!.playTogether(animator, animator2)
                        obj.animatorSet!!.setDuration(1000)
                        obj.animatorSet!!.start()
                */
                holder.photo.visibility = View.GONE
            }
            notifyDataSetChanged()
        }

    }


    class FlickViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo = view.flickr_image
        var title = view.flickr_title

    }

    fun setList1(list: ArrayList<FlickrPhotoObject>) {
        this.list = list
        notifyDataSetChanged()
    }
}