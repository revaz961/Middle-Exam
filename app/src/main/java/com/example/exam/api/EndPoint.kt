package com.example.exam.api

enum class EndPoint {
    CHARACTER {
        override fun getEndPoint() = "character"
    },
    LOCATION {
        override fun getEndPoint() = "location"
    },
    EPISODE {
        override fun getEndPoint() = "episode"
    };


    abstract fun getEndPoint():String
}