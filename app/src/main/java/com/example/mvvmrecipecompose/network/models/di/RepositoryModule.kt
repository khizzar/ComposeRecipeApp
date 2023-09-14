package com.example.mvvmrecipecompose.network.models.di

import com.example.mvvmrecipecompose.network.models.RecipeDtoMapper
import com.example.mvvmrecipecompose.network.models.RecipeService
import com.example.mvvmrecipecompose.network.models.repository.RecipeRepository
import com.example.mvvmrecipecompose.network.models.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository{
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}