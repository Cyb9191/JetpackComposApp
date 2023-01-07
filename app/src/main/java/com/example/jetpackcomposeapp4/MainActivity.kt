package com.example.jetpackcomposeapp4
import android.os.Bundle
import com.example.jetpackcomposeapp4.ui.theme.Qd
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.preferencesDataStore
import androidx.compose.runtime.livedata.observeAsState
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyVerticalGrid
//import com.codelab.basics.ui.theme.BasicsCodelabTheme
import androidx.compose.foundation.layout.width
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeapp4.ui.theme.ListLevel
import com.example.jetpackcomposeapp4.ui.theme.Slz
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
                MyApp(modifier = Modifier.fillMaxSize())
            }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greeting()
        }
    }
}
val lc:List<Color> = listOf(Color.Red, Color.Blue, Color.Gray, Color.DarkGray, Color.Magenta, Color.LightGray, Color.Green,Color.Yellow)
@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier,

) {


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (column1,column2,column3) = createRefs()
        val lstclm= listOf(column1,column2,column3)
        val lcl = LocalConfiguration.current.screenWidthDp.dp / 6
        val lcl2=LocalConfiguration.current.screenHeightDp.dp / 10
        for (nc:Int in lstclm.indices){

    LazyColumn(

        modifier = Modifier.constrainAs(lstclm[nc]){
            if (nc==0){

            start.linkTo(parent.start)
        }
            else{
                absoluteLeft.linkTo(lstclm[nc-1].absoluteRight, margin = lcl);
                top.linkTo(lstclm[nc-1].top, margin = lcl2);
            }}

    ) { items((List(10){0}).subList(0,10-(2*nc))){
        //data.indexOf(element = item)!=8

                Box(
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp / 6)
                        .height(LocalConfiguration.current.screenHeightDp.dp / 10)
                        //elevation = ButtonDefaults.elevation(5.dp),
                        .background(color = lc[(lc.indices).random()]),
                    //contentPadding = PaddingValues(
                ){


            }
        }
    }

            Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("START")
        }
    }
}}}



@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    //BasicsCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
   // }
}
fun Crqdrscreen(lstdq: MutableList<QdrScreen>, lv:Int) {
    for (idxqd:Int in 0..59){
        for (qd:Qd in ListLevel.lislevel[lv].listqd){
            if (qd.idx==idxqd){
                lstdq.add(QdrScreen("qdr",qd.idx,Color.Blue,qd.hrstr,qd.hlstr,qd.vbtstr,qd.vtstr, ""))
            }
        }
        for (sz:Slz in ListLevel.lislevel[lv].listsz){
            if ((sz.idxch==idxqd) or  (sz.idxch< idxqd && idxqd< (sz.idxch+sz.parola.length)) ){
                lstdq.add(QdrScreen("ltr",idxqd,Color.Transparent,"","","","",sz.parola.substring(idxqd-sz.idxch,idxqd-sz.idxch+1)))
            }
        }
    }

}

@Composable
private fun Greeting() {
    val context= LocalContext.current





    var lstdq:MutableList<QdrScreen> = mutableListOf()
    Crqdrscreen(lstdq,0)
    for (qdr:QdrScreen in lstdq){
        Log.e("QDR"+"_"+qdr.idx.toString(),qdr.typ+"__"+qdr.chsl)
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (column2_1,column2_2,column2_3,column2_4,column2_5,column2_6,column2_7,column2_8,column2_9,column2_10) = createRefs()
        val lstclm= listOf(column2_1,column2_2,column2_3,column2_4,column2_5,column2_6,column2_7,column2_8,column2_9,column2_10)
        val lcl = LocalConfiguration.current.screenWidthDp.dp / 6
        val lcl2=LocalConfiguration.current.screenHeightDp.dp / 10
        for (nc:Int in lstclm.indices){

            LazyRow(


                modifier = Modifier.constrainAs(lstclm[nc]){

                    if (nc==0){

                        absoluteLeft.linkTo(parent.absoluteLeft)
                        //absoluteRight.linkTo(parent.absoluteRight)
                        top.linkTo(parent.top)
                    }
                    else{
                        top.linkTo(lstclm[nc-1].bottom,);
                        absoluteLeft.linkTo(lstclm[nc-1].absoluteLeft);
                        absoluteRight.linkTo(lstclm[nc-1].absoluteRight)
                    }}

            ) {
                items(lstdq.subList(nc*6,nc*6+6)){
                //data.indexOf(element = item)!=8

                if (it.typ=="qdr"){
                    Box(modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp / 6)
                        .height(LocalConfiguration.current.screenHeightDp.dp / 10)
                        //elevation = ButtonDefaults.elevation(5.dp),
                        .background(
                            color = lc[(lc.indices).random()]
                        )){
                Button(
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp / 6)
                        .height(LocalConfiguration.current.screenHeightDp.dp / 10)
                        //elevation = ButtonDefaults.elevation(5.dp),
                        .background(color = Color.Black)

                        ,
                    onClick = {
                        Toast.makeText(context, generataButtonText(it),4).show()
                    }
                    //contentPadding = PaddingValues(
                ){


                }
            }}
                if (it.typ=="ltr"){

                    val szqd=it.chsl
                Box(
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp / 6)
                        .height(LocalConfiguration.current.screenHeightDp.dp / 10)
                        //elevation = ButtonDefaults.elevation(5.dp),
                        .background(color = Color.Cyan)
                        .shadow(20.dp))

                    {
                        var tx by remember {
                            mutableStateOf(TextFieldValue(""))

                        }
                        var readOnlyVar by remember{
                        mutableStateOf(false)

                    }
                        var colorTx by remember{
                            mutableStateOf(Color.White)

                        }

                BasicTextField(modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp / 6)
                    .height(LocalConfiguration.current.screenHeightDp.dp / 10)
                    //elevation = ButtonDefaults.elevation(5.dp),
                    //.background(color = Color.White)
                    .shadow(20.dp),
                    readOnly = readOnlyVar
                    ,value = tx, onValueChange = {
                    tx=it
                        Log.e("SCESL",it.text+"__sol:__"+szqd)
                        readOnlyVar = (it.text.uppercase()==szqd.uppercase())
                        if (readOnlyVar){
                            colorTx= Color.White
                            } else{
                            colorTx=Color.Magenta
                        }
                },

                    textStyle = LocalTextStyle.current.copy(color = colorTx, textAlign = TextAlign.Center, fontSize = 40.sp))

        }
    }}}}}}
fun generataButtonText(qdr:QdrScreen): String {
    var tx:String= ""
    if (qdr.hrightstr.isNotEmpty()){
        tx = tx+ "O. DX.: "+qdr.hrightstr + "\n"
    }
    if (qdr.hleftstr.isNotEmpty()){
        tx = tx+ "O. SX.: "+qdr.hleftstr + "\n"
    }
    if (qdr.vtopstr.isNotEmpty()){
        tx = tx+ "V. A.: "+qdr.vtopstr + "\n"
    }
    if (qdr.vbottomstr.isNotEmpty()){
        tx = tx+ "V. B.: "+qdr.vbottomstr
    }

    return(tx)
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    //BasicsCodelabTheme {
        Greeting()
  //  }
}

@Preview
@Composable
fun MyAppPreview() {
    // BasicsCodelabTheme {
        MyApp(Modifier.fillMaxSize())
    //}
}