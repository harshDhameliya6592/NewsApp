package com.example.newsfresh

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import kotlin.reflect.KProperty

class MySingleton constructor(context: Context) {
    fun getHeaders(): MutableMap<String, String> {
        val headers = HashMap<String, String>()
        headers["Authorization"] = "de7e587637d74772b311fc6fd7fe11e2"
        return headers
    }
    companion object {
        @Volatile
        private var INSTANCE: MySingleton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MySingleton(context).also {
                    INSTANCE = it
                }
            }


    }
//    val imageLoader: ImageLoader by lazy {
//        ImageLoader(requestQueue,
//            object : ImageLoader.ImageCache {
//                private val cache = LruCache<String, Bitmap>(20)
//                override fun getBitmap(url: String): Bitmap {
//                    return cache.get(url)
//                }
//                override fun putBitmap(url: String, bitmap: Bitmap) {
//                    cache.put(url, bitmap)
//                }
//            })
//    }





   private val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)

    }
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}






