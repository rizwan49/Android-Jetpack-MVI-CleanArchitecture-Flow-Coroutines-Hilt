package com.rizwan.cinehub.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rizwan.cinehub.data.source.local.LocalMovieModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * this manager class will help to read the json file from assets and convert it to required type
 */


class MoviesManager @Inject constructor(
    @ApplicationContext val context: Context
) {
    companion object {
        const val PAGE_1 = "apis/page1.json"
        const val PAGE_2 = "apis/page2.json"
        const val PAGE_3 = "apis/page3.json"
    }

    private val gson = Gson()

    /**
     * this method will required to get the json data from assets files
     * @param page : required to get the specific page.
     */
    fun getAllMovies(page: Int): LocalMovieModel {
        val fileName = when (page) {
            1 -> PAGE_1
            2 -> PAGE_2
            else -> PAGE_3
        }

        val jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        Log.d("Rizwan", "MoviesManager called : json -> $jsonString")

        //        val fileNames = arrayOf(, "apis/page2.json", "apis/page3.json")
//        for (fileName in fileNames) {
//            val jsonString = context.assets.open(fileName).bufferedReader().use {
//                it.readText()
//            }
//            val moviesInFile: List<LocalMovieModel> =
//                gson.fromJson(jsonString, object : TypeToken<List<LocalMovieModel>>() {}.type)
//            moviesList.addAll(moviesInFile)
//        }
        return gson.fromJson(jsonString, object : TypeToken<LocalMovieModel>() {}.type)
    }
}