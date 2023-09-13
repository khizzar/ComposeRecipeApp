package com.example.mvvmrecipecompose.network.models.response

import com.example.mvvmrecipecompose.network.models.RecipeNetworkEntity
import com.google.gson.annotations.SerializedName

class RecipeSearchResponse (
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeNetworkEntity>,
)