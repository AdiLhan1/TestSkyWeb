package com.jacob.testskyweb.utils

import java.util.regex.Pattern

object Constants {
    const val BASE_URL = "https://picsum.photos/"
    const val BASE_URL_WEATHER = "https://api.openweathermap.org/data/2.5/weather"
    const val LANG = "ru"
    const val UNITS = "metric"
    const val APP_ID = "c35880b49ff95391b3a6d0edd0c722eb"

    private const val PASSWORD_PATTERN =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$"

    val pattern: Pattern = Pattern.compile(PASSWORD_PATTERN)
}