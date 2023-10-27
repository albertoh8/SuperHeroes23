package com.iesam.superheroes23.app


sealed class ErrorApp {
    object UnknowwError: ErrorApp()
    object InternetError: ErrorApp()
    object DataError: ErrorApp()
}