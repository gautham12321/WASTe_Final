package com.example.waste.data

enum class STATUS{
    SALVAGED,
    SCRAP

}
class product(val name:String="Redmi",val price:Int=500,val Status:STATUS =STATUS.SALVAGED,val type:String) {
}
val UserList:List<product> = listOf()