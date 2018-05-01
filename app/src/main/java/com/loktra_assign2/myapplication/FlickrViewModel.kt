package com.loktra_assign2.myapplication

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by user on 4/30/2018.
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

                        var photoList = response!!.body()!!.photos.photo
                        for (i in 0..photoList.size - 1) {
                            var obj = photoList.get(i)
                            var url = "https://farm${obj.farm}.staticflickr.com/${obj.server}/${obj.id}_${obj.secret}.jpg"
                            var title = obj.title
                            var photObject = FlickrPhotoObject(url, title, false,null)
                            displayPhotoList.add(photObject)
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