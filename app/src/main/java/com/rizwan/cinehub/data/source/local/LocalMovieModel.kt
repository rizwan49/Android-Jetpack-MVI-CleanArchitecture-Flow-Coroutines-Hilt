package com.rizwan.cinehub.data.source.local

import com.google.gson.annotations.SerializedName

/**
 * Movie list response model class
 */
data class LocalMovieModel(
    @SerializedName("page")
    val page: Page
)

data class Page(
    @SerializedName("content-items")
    val contentItems: ContentItems,
    @SerializedName("page-num")
    val pageNum: String,
    @SerializedName("page-size")
    val pageSize: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("total-content-items")
    val totalContentItems: String
)

data class ContentItems(
    @SerializedName("content")
    val content: List<Content>
)

data class Content(
    @SerializedName("name")
    val name: String,
    @SerializedName("poster-image")
    val posterImage: String
)

