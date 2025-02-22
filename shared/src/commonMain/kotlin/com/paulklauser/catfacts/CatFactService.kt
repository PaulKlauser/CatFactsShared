package com.paulklauser.catfacts

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class CatFactService {
    private val client = HttpClient()

    suspend fun fetchCatFact(): String {
        return client.get("https://catfact.ninja/fact").bodyAsText()
    }
}