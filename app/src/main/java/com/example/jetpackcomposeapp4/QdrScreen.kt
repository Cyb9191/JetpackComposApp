package com.example.jetpackcomposeapp4
import androidx.compose.ui.graphics.Color
import kotlin.reflect.typeOf

class QdrScreen(typ:String,idx:Int, clr:Color,hrightstr:String="",hleftstr:String="",vbottomstr:String="", vtopstr:String="",chsl:String) {
    val typ:String= typ
    val idx:Int= idx
    val clr:Color=clr
    val hrightstr:String= hrightstr
    val hleftstr:String= hleftstr


    val vtopstr:String=vtopstr
    val vbottomstr:String= vbottomstr

    val chsl:String =chsl
}