package com.example.mvvmrecipecompose.network.models.repository

import com.example.mvvmrecipecompose.domain.Recipe

interface RecipeRepository {

    suspend fun search(token: String, page: Int, query: String): List<Recipe>

    suspend fun get(token: String, id: Int): Recipe

}