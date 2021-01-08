package com.example.eatthalal.ui.main.categories.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("count")
    @Expose
    val count: Int?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("display")
    @Expose
    val display: String?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("image")
    @Expose
    val image: Image?,
    @SerializedName("_links")
    @Expose
    val links: Links?,
    @SerializedName("menu_order")
    @Expose
    val menuOrder: Int?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("parent")
    @Expose
    val parent: Int?,
    @SerializedName("slug")
    @Expose
    val slug: String?
)
    data class Image(
        @SerializedName("alt")
        @Expose
        val alt: String?,
        @SerializedName("date_created")
        @Expose
        val dateCreated: String?,
        @SerializedName("date_created_gmt")
        @Expose
        val dateCreatedGmt: String?,
        @SerializedName("date_modified")
        @Expose
        val dateModified: String?,
        @SerializedName("date_modified_gmt")
        @Expose
        val dateModifiedGmt: String?,
        @SerializedName("id")
        @Expose
        val id: Int?,
        @SerializedName("name")
        @Expose
        val name: String?,
        @SerializedName("src")
        @Expose
        val src: String?
    )

    data class Links(
        @SerializedName("collection")
        @Expose
        val collection: List<Collection?>?,
        @SerializedName("self")
        @Expose
        val self: List<Self?>?
    )
        data class Collection(
            @SerializedName("href")
            @Expose
            val href: String?
        )

        data class Self(
            @SerializedName("href")
            @Expose
            val href: String?
        )

