package com.loktra_assign2.myapplication

/**
 * Created by user on 5/1/2018.
 */
data class FlickrMetaObject(var page:Long,var pages:Long,var perpage:Long,var total:String
                       ,var photo:ArrayList<FlickrSearchObject>)
