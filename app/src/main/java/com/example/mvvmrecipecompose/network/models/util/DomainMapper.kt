package com.example.mvvmrecipecompose.network.models.util

interface DomainMapper <T, DomainModel> { // T -> RecipeNetworkEntity, DomainModel -> Recipe

    fun mapToDomainModel(entity: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T

}