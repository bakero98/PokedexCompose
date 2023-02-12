package com.example.pokedexcompose.repo

import com.example.pokedexcompose.data.remote.PokeApi
import com.example.pokedexcompose.data.remote.responses.Pokemon
import com.example.pokedexcompose.data.remote.responses.PokemonList
import com.example.pokedexcompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepo @Inject constructor(
    private val pokeApi: PokeApi
) {
    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Resource<PokemonList> {
        val response = try {
            pokeApi.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonDetails(
        pokemonName: String
    ): Resource<Pokemon> {
        val response = try {
            pokeApi.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }
}