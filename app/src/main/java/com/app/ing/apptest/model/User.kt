package com.app.ing.apptest.model


/**
 * Created by jose on 27/03/18.
 */

data class UserResponse(var results:ArrayList<User>)
data class User (var name: Name, var location: Location, var email:String, var phone:String, var picture:Picture)
data class Name(var title: String, var first: String, var last:String)
data class Location(var street:String, var city : String, var state: String)
data class Picture(var large:String, var medium:String,var thumbnail:String)
data class UserDatabase(var name: String, var location: String, var email :String, var imgUrl:String, var  phone: String)