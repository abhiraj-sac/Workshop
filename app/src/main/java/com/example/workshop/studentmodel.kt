package com.example.workshop

import kotlin.random.Random

data class studentmodel (
    var id:Int = Autogen(),
    var name:String = "",
    var email:String = ""
)
{
    companion object{
   fun Autogen():Int{
        val random = java.util.Random()
        return random.nextInt(100)
    }
}
}
