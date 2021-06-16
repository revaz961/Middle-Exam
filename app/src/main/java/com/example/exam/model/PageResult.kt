package com.example.exam.model

data class PageResult<T>(
    val info:Info?,
    val results:List<T>?
)