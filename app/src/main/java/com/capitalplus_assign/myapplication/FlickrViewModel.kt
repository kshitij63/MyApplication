package com.capitalplus_assign.myapplication

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by user on 6/6/2018.
 */
class FlickrViewModel : ViewModel() {
    var mutableLiveData = MutableLiveData<ArrayList<FlickrPhotoObject>>()
    fun getPhotoMetaList(str: String) {

        FlickrRetrofitClient.getRetrofitClient()
                .create(FlickrApiInterface::class.java)
                .getAllPhotosData("flickr.photos.search", "1",
                        "json", str, "2155e9406043b7494453105eec99ae37")
                .enqueue(object : Callback<FlickrObject> {
                    override fun onFailure(call: Call<FlickrObject>?, t: Throwable?) {
                        Log.e("Failure ", t!!.message)
                    }

                    override fun onResponse(call: Call<FlickrObject>?, response: Response<FlickrObject>?) {
                        //     Log.e("response is : ", response!!.body().toString())
                        var displayPhotoList = ArrayList<FlickrPhotoObject>()

                        var i = 0;
                        var photoList = response!!.body()!!.photos.photo
                        while (i + 1 < photoList.size - 1) {
                            var obj = photoList.get(i)
                            var obj2 = photoList.get(i + 1)
                            var url = "https://farm${obj.farm}.staticflickr.com/${obj.server}/${obj.id}_${obj.secret}.jpg"
                            var url2 = "https://farm${obj2.farm}.staticflickr.com/${obj2.server}/${obj2.id}_${obj2.secret}.jpg"

                            var title = obj.title
                            var title2 = obj2.title
                            var photObject = FlickrPhotoObject(url, title, false, url2, title2, false, false)
                            displayPhotoList.add(photObject)
                            i = i + 2
                        }
                        mutableLiveData.postValue(displayPhotoList)
                    }

                })
    }

    fun getPhotoList(str: String): LiveData<ArrayList<FlickrPhotoObject>> {

        getPhotoMetaList(str)
        return mutableLiveData
    }
}