package com.example.mvvmrecipecompose.network.models.util

interface EntityMapper <Entity, DomainModel> { // Entity -> RecipeNetworkEntity, DomainModel -> Recipe

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity

}