package com.loktra_assign2.myapplication

import android.animation.ObjectAnimator
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.single_photo_view.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter = FlickrAdapter(this@MainActivity, ArrayList<FlickrPhotoObject>())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        var model = ViewModelProviders.of(this).get(FlickrViewModel::class.java)
        model.getPhotoList("tiger").observe(this, object : Observer<ArrayList<FlickrPhotoObject>> {
            override fun onChanged(t: ArrayList<FlickrPhotoObject>?) {
                Log.e("size is", "${t!!.size}")
                adapter.setList1(t!!)

            }
        })
        edit.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    if (edit.text.toString() != null) {
                        prog.visibility = View.VISIBLE
                        model.getPhotoList(edit.text.toString()).observe(this@MainActivity, object : Observer<ArrayList<FlickrPhotoObject>> {
                            override fun onChanged(t: ArrayList<FlickrPhotoObject>?) {
                                prog.visibility = View.GONE
                                Log.e("size is", "${t!!.size}")
                                if (t.size == 0)
                                    Toast.makeText(this@MainActivity, "Nothing to show", Toast.LENGTH_SHORT)
                                adapter.setList1(t)
                                //var adapter = FlickrAdapter(this@MainActivity, t)
                                //recyclerView.adapter = adapter
                                //  recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                            }
                        })
                    } else {
                        Toast.makeText(this@MainActivity, "Invalid text", Toast.LENGTH_SHORT)
                    }

                    return true
                }

                return false
            }
        })
    }


}
