package com.example.expancetracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.expancetracker.ui.theme.darkgreen

@Composable
 fun HomeScreen() {
     Surface(modifier = Modifier.fillMaxSize()) {
         ConstraintLayout(modifier = Modifier.fillMaxSize()) {
             val (nameRow,list,card,topBar) = createRefs()
             Image(painter = painterResource(id = R.drawable.ic_topbar), contentDescription =null,
                modifier = Modifier.constrainAs(topBar){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end) })
             Box(modifier = Modifier
                 .fillMaxWidth()
                 .padding(top = 64.dp, start = 16.dp, end = 16.dp)
                 .constrainAs(nameRow) {
                     top.linkTo(parent.top)
                     start.linkTo(parent.start)
                     end.linkTo(parent.end)
                 }) {
                 Column {
                     Text(text = "Good AfterNoon", fontSize = 16.sp, color = Color.White)
                     Text(text = "Kaushal", fontSize = 20.sp, fontWeight = FontWeight.Bold,color = Color.White)

                 }
                 Image(painter = painterResource(id = R.drawable.ic_notification),
                     contentDescription =null,
                     modifier = Modifier.align(Alignment.CenterEnd)
                 )
             }
             cardItem(modifier =Modifier
                 .constrainAs(card){
                     top.linkTo(nameRow.bottom)
                     start.linkTo(parent.start)
                     end.linkTo(parent.end)
                 })
             TransationList(modifier = Modifier
                 .fillMaxWidth()
                 .constrainAs(list) {
                     top.linkTo(card.bottom)
                     start.linkTo(parent.start)
                     end.linkTo(parent.end)
                     bottom.linkTo(parent.bottom)
                     height = Dimension.fillToConstraints
                 })
         }
     }
}
@Composable
fun cardItem(modifier: Modifier){
    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(darkgreen)
        .padding(16.dp),

        ) {
        Box(modifier= Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Column (modifier=Modifier.align(Alignment.CenterStart)){
                Text(text ="Total Balance", fontSize = 16.sp, color = Color.White)
                Text(
                    text ="INR 100000",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Image(painter = painterResource(id = R.drawable.dots_menu),
                contentDescription =null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )

        }

        Box (modifier= Modifier
            .fillMaxWidth()
            .weight(1f)){

            cardRowitem(
                modifier = Modifier.align(Alignment.CenterStart),
                title = "Income",
                amount = "INR 100000",
                image = R.drawable.ic_income
            )
            cardRowitem(
                modifier = Modifier.align(Alignment.CenterEnd),
                title = "Expense",
                amount = "INR 50000",
                image = R.drawable.ic_expense
            )
        }

    }
}

@Composable
fun TransationList(modifier: Modifier){
    Column (modifier=modifier.padding(horizontal = 16.dp)){
        Box(modifier = Modifier.fillMaxWidth()){
            Text(text = "Recent Transaction", fontSize = 20.sp)
            Text(text = "See All",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterEnd))
        }
        TranstionItem(Title = "Netflix", amount = "- INR 500.0", icoms = R.drawable.ic_netflix, date = "Today", color =Color.Red )
        TranstionItem(Title = "Paypal", amount = "+ INR 1500.0", icoms = R.drawable.ic_paypal, date = "Today", color =Color.Red )
        TranstionItem(Title = "UpWork", amount = "+ INR 6000.0", icoms = R.drawable.ic_upwork, date = "Today", color =Color.Red )
        TranstionItem(Title = "Youtube", amount = "- INR 199.0", icoms = R.drawable.ic_youtube, date = "Today", color =Color.Red )
        TranstionItem(Title = "Starbucks", amount = "- INR 650.0", icoms = R.drawable.ic_starbucks, date = "Today", color =Color.Red )


    }

}

@Composable
fun TranstionItem(Title:String,amount: String,icoms:Int,date:String,color:Color){
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)){
        Row {
            Image(painter = painterResource(id = icoms), contentDescription =null,
                modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(text = Title, fontSize = 16.sp)
                Text(text = date, fontSize = 12.sp)
            }
        }
        Text(text = amount, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterEnd), color = color)
    }
}

@Composable
fun cardRowitem(modifier: Modifier,title:String,amount:String,image:Int){
    Column (modifier=modifier){
        Row {
            Image(painter = painterResource(id = image), contentDescription = null)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = title, fontSize = 16.sp, color = Color.White)
        }
        Text(text = amount, fontSize = 16.sp, color = Color.White)
    }

}




@Composable
@Preview(showBackground = true)
fun previewHomeScreen(){
    HomeScreen()
}