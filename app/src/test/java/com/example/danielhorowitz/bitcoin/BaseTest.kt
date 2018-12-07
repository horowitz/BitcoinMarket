package com.example.danielhorowitz.bitcoin

import java.io.File

fun getResource(fileName: String): File {
    val loader = ClassLoader.getSystemClassLoader()
    val resource = loader.getResource(fileName)
    return File(resource.path)
}