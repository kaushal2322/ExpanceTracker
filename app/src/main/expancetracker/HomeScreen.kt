package com.example.expancetracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expancetracker.data.model.ExpenseEntity
import com.example.expancetracker.ui.theme.darkgreen
import com.example.expancetracker.viewmodel.HomeViewModelFactory
import com.example.expancetracker.viewmodel.HomeviewModel

@Composable
 fun HomeScreen(navController: NavController) {
     val viewModel =HomeViewModelFactory(LocalContext.current).create(HomeviewModel::class.java)


     Surface(modifier = Modifier.fillMaxSize()) {
         ConstraintLayout(modifier = Modifier.fillMaxSize()) {
             val (nameRow,list,card,topBar,add) = createRefs()
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
                     Text(text = "Expense Tracker", fontSize = 20.sp, color = Color.White)
                     Text(text = "Made By Kaushal", fontSize = 10.sp, fontWeight = FontWeight.Bold,color = Color.White)

                 }
                 Image(painter = painterResource(id = R.drawable.ic_notification),
                     contentDescription =null,
                     modifier = Modifier.align(Alignment.CenterEnd)
                 )
             }
             val state = viewModel.expenses.collectAsState(initial = emptyList())
             val expense = viewModel.getTotalExpense(state.value)
             val income = viewModel.getTotalIncome(state.value)
             val balance = viewModel.getBalance(state.value)


             cardItem(modifier =Modifier
                 .constrainAs(card){
                     top.linkTo(nameRow.bottom)
                     start.linkTo(parent.start)
                     end.linkTo(parent.end)
                 },balance,income,expense)
             TransationList(modifier = Modifier
                 .fillMaxWidth()
                 .constrainAs(list) {
                     top.linkTo(card.bottom)
                     start.linkTo(parent.start)
                     end.linkTo(parent.end)
                     bottom.linkTo(parent.bottom)
                     height = Dimension.fillToConstraints
                 },list= state.value,viewModel)
             Image(painter = painterResource(id = R.drawable.add), contentDescription =null , modifier = Modifier.constrainAs(add)

             {
                 bottom.linkTo(parent.bottom)
                 end.linkTo(parent.end)
             }.size(48.dp).clickable {
                 navController.navigate("/add")

             }
             )
         }
     }
}
@Composable
fun cardItem(modifier: Modifier, balance: String, income: String, expense: String){
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
                    text =balance,
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
                amount = income,
                image = R.drawable.ic_income
            )
            cardRowitem(
                modifier = Modifier.align(Alignment.CenterEnd),
                title = "Expense",
                amount = expense,
                image = R.drawable.ic_expense
            )
        }

    }
}

@Composable
fun TransationList(modifier: Modifier,list: List<ExpenseEntity>,viewModel:HomeviewModel){
    LazyColumn(modifier=modifier.padding(horizontal = 16.dp)){
        item{
            Box(modifier = Modifier.fillMaxWidth()){
                Text(text = "Recent Transaction", fontSize = 20.sp)
                Text(text = "See All",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterEnd))
            }
        }
        items(list){
            item ->
            TranstionItem(Title = item.title, amount = item.amount.toString(),
                icoms =viewModel.geItemIcon(item),
                date = item.data.toString(),
                color = if(item.type=="Income") Color.Green else Color.Red)
        }



    }

}

@Composable
fun TranstionItem(Title:String,amount: String,icoms:Int,date:String,color:Color){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)){
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
    HomeScreen(rememberNavController())
}