package com.capitalplus_assign.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_photo_view.view.*


/**
 * Created by user on 6/6/2018.
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
    //    obj.uniq = false
      //  if (obj.uniq == false) {
        //    holder.title.visibility = View.GONE
//            obj.rot2 = false
  //          obj.rotated = false

        //}
        Picasso.get().load(obj.url).placeholder(R.drawable.placeholder).into(holder.photo)
        Picasso.get().load(obj.url2).placeholder(R.drawable.placeholder).into(holder.photo2)

        holder.photo.setOnClickListener {
            if (obj.rotated == false && obj.rot2 == false) {
                obj.rotated = true
                holder.title.visibility = View.VISIBLE
                holder.title.setText(obj.title)

            } else if (obj.rot2 == true && obj.rotated == true) {
                obj.rotated = false
                obj.rot2 = false
                holder.title.visibility = View.INVISIBLE
            } else if (obj.rotated == false && obj.rot2 == true) {
                obj.rot2 = false
                obj.rotated = true
                holder.title.text = obj.title
            } else {
                obj.rotated = false
                holder.title.visibility = View.GONE

            }
            notifyDataSetChanged()


        }

        holder.photo2.setOnClickListener {
            if (obj.rotated == false && obj.rot2 == false) {
                obj.rot2 = true
                holder.title.visibility = View.VISIBLE
                holder.title.setText(obj.title2)

            } else if (obj.rot2 == true && obj.rotated == true) {
                obj.rot2 = false
                obj.rotated = false
                holder.title.visibility = View.INVISIBLE
            } else if (obj.rotated == false && obj.rot2 == true) {
                obj.rot2 = false
                holder.title.visibility = View.GONE
            } else {
                obj.rot2 = true
                obj.rotated = false
                holder.title.text = obj.title2

            }
            notifyDataSetChanged()

        }


    }


    class FlickViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo = view.flickr_image
        var title = view.title
        var photo2 = view.flickr_image2

    }

    fun setList1(list: ArrayList<FlickrPhotoObject>) {
        this.list = list
        notifyDataSetChanged()
    }
}