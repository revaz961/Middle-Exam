package com.example.exam

import com.example.exam.adapter.NestedCharacterAdapter


typealias Click<T> = (data: T) -> Unit

typealias LoadData<T> = (adapter: NestedCharacterAdapter, List<T>) -> Unit