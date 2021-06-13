package com.example.exam.api.model

data class PageResult<T>(
    val info:Info?,
    val results:List<T>?
)