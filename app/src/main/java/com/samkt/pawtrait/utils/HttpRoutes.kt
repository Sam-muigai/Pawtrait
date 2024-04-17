package com.samkt.pawtrait.utils

object HttpRoutes {

    fun getCats(limit: Int): String = "https://api.thecatapi.com/v1/images/search?limit=$limit"
}