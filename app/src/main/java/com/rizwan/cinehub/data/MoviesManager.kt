package com.rizwan.cinehub.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rizwan.cinehub.data.source.local.Content
import com.rizwan.cinehub.data.source.local.LocalMovieModel
import com.rizwan.cinehub.domain.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * this manager class will help to read the json file from assets and convert it to required type
 */


class MoviesManager @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    @ApplicationContext val context: Context
) {
    companion object {
        const val PAGE_1 = "apis/page1.json"
        const val PAGE_2 = "apis/page2.json"
        const val PAGE_3 = "apis/page3.json"
    }

    private val gson = Gson()
    private val fileNames = arrayOf(PAGE_1, PAGE_2, PAGE_3)

    /**
     * this method will required to get the json data from assets files
     * @param page : required to get the specific page.
     */
    suspend fun getAllMovies(page: Int): LocalMovieModel {
        return withContext(dispatcher) {
            val fileName = when (page) {
                1 -> PAGE_1
                2 -> PAGE_2
                else -> PAGE_3
            }

            val jsonString = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }

            Log.d("Rizwan", "MoviesManager called : json -> $jsonString")
            gson.fromJson(jsonString, object : TypeToken<LocalMovieModel>() {}.type)
        }
    }

    suspend fun getSearchedMoviesList(searchedText: String): List<Content> {
        return withContext(dispatcher) {
            val searchList: MutableList<Content> = mutableListOf()
            for (fileName in fileNames) {

                val jsonString = context.assets.open(fileName).bufferedReader().use {
                    it.readText()
                }

                val moviesInFile = jsonString.fromJson<LocalMovieModel>() //using extension function

                searchList.addAll(moviesInFile.page.contentItems.content.filter { content ->
                    content.name.contains(searchedText, ignoreCase = true)
                })

                Log.d("search found", "${searchList.size}")
            }
            searchList
        }
    }
}