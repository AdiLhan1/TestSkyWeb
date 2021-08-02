package com.jacob.testskyweb.repository

import com.jacob.testskyweb.network.ApiService
import com.jacob.testskyweb.utils.Constants.APP_ID
import com.jacob.testskyweb.utils.Constants.BASE_URL_WEATHER
import com.jacob.testskyweb.utils.Constants.LANG
import com.jacob.testskyweb.utils.Constants.UNITS
import javax.inject.Inject

class Repository
@Inject constructor(private val api: ApiService) {
    suspend fun getFilms(page: Int, limit: Int) = api.getFilms(page, limit)
    suspend fun getWeathers(id: Int) = api.getWeathers(BASE_URL_WEATHER, id, APP_ID, LANG, UNITS)
}