package com.example.exam.network

enum class EndPoint {
    CHARACTER {
        override val endPoint: String
            get() = "character"

    },
    LOCATION {
        override val endPoint: String
            get() = "location"

    },
    EPISODE {
        override val endPoint: String
            get() = "episode"

    };


    abstract val endPoint:String
}