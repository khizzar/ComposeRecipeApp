package com.example.mvvmrecipecompose.network.models.response

import com.example.mvvmrecipecompose.network.models.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse (
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)