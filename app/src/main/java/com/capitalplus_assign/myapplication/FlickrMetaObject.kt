package com.capitalplus_assign.myapplication

/**
 * Created by user on 6/6/2018.
 */
data class FlickrMetaObject(var page:Long,var pages:Long,var perpage:Long,var total:String
                       ,var photo:ArrayList<FlickrSearchObject>)
