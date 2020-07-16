package com.sopt.ounce.main.data

data class RequestSelectedFilter(
    val foodManu : MutableList<String>?,
    val foodDry : MutableList<String>?,
    val foodMeat : MutableList<String>?
)