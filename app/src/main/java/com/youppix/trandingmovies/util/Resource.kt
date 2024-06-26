package com.youppix.trandingmovies.util

sealed class Resource<T> (val data : T? = null , val message : String? = null){

    class Successful <T>(data: T?) : Resource<T>(data)

    class Error<T> (message: String? , data: T? = null) : Resource<T>(data , message)

    class Loading<T> (data: T? = null) : Resource<T>(data)

}