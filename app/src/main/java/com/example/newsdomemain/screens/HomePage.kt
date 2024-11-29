package com.example.newsdomemain.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.newsdomemain.R
import com.example.newsdomemain.data.model.Article
import com.example.newsdomemain.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val josefinSans = FontFamily(
    Font(R.font.josefin)
)
val inter = FontFamily(
    Font(R.font.inter)
)
val jacques1 = FontFamily(
    Font(R.font.jacques)
)

val noticia1 = FontFamily(
    Font(R.font.noticia)
)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(articles: List<Article>) {
    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val selectedItem = remember {
        mutableStateOf("")
    }
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val isAlertBoxOpen = remember { mutableStateOf(false) }

    val rowList = remember {
        mutableListOf(
            "Trending", "Business", "Entertainment", "Health", "Science", "Sports", "Technology"
        )
    }
    val d = remember { mutableStateOf(false) }

    var articleData: Article? = null


    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                "NEWS DOME",
                modifier = Modifier.padding(start = 20.dp),
                style = TextStyle(fontSize = 24.sp, fontFamily = josefinSans)
            )

        }, actions = {
            IconButton(onClick = {

                isDropDownExpanded.value = !isDropDownExpanded.value
            }) {
                Icon(
                    painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            DropdownMenu(
                onDismissRequest = { isDropDownExpanded.value = false },
                expanded = isDropDownExpanded.value
            ) {
                DropdownMenuItem(text = { Text("About Me") }, onClick = {
                    isAlertBoxOpen.value = true
                    isDropDownExpanded.value = false
                })
            }
            if (isAlertBoxOpen.value) {
                AlertDialog(
                    onDismissRequest = { isAlertBoxOpen.value = false },
                    confirmButton = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {
                                isAlertBoxOpen.value = false
                            }) { Text("Close") }
                        }
                    },
                    title = { Text("News Dome") },
                    text = {
                        Column() {
                            Text("Version: 1.0")
                            Text("Developed by: Aman")
                        }
                    }, shape = RectangleShape
                )
            }
        })
    }) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, color = Color.Gray)
                    .height(58.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(rowList) { category ->
                    CategoryRowList(category, isSelected = selectedItem.value == category) {
                        selectedItem.value = category
                        coroutineScope.launch {
                            viewModel.fetchCategories("india " + category.lowercase())
                            delay(800)
                            listState.scrollToItem(0)
                        }
                    }
                }


            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(), state = listState
            ) {
                items(articles.take(99)) { article ->

                    if (article.title != "[Removed]" && !article.title.isNullOrBlank() && !article.description.isNullOrBlank() && !article.urlToImage.isNullOrBlank()) {
                        NewsListItem(
                            article,
                            onClick = {
                                articleData = article
                                d.value = true

                            },
                        )


                    }
                }

            }
            if (d.value) {
                ModalBottomSheet(onDismissRequest = { d.value = false }) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(bottom = 24.dp)
                    ) {
                        articleData?.let {
                            Text(
                                it.title,

                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(horizontal = 20.dp),
                                style = TextStyle(
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = jacques
                                )
                            )
                        }
                        Spacer(Modifier.height(24.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            Text(
                                "By: ${articleData?.author}",
                                modifier = Modifier
                                    .width(150.dp)
                                //.weight(1f),
                                ,
                                style = TextStyle(fontFamily = FontFamily.Serif, fontSize = 12.sp)
                            )
                            Text(
                                "Published ${articleData?.publishedAt?.substringAfterLast("T")}",
                                modifier = Modifier
                                    .width(150.dp)
                                //   .weight(1f),
                                ,
                                style = TextStyle(fontFamily = FontFamily.Serif, fontSize = 12.sp)
                            )
                        }
                        Spacer(Modifier.height(32.dp))
                        articleData?.let {
                            Text(
                                it.content.substringBefore("[+").trim(),
                                modifier = Modifier.padding(horizontal = 20.dp),
                                style = TextStyle(fontSize = 16.sp, fontFamily = noticia)
                            )
                        }

                        Text(
                            "Read the original article",
                            style = TextStyle(
                                color = Color.Blue,
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.Underline,
                                fontFamily = FontFamily.Serif
                            ),
                            modifier = Modifier
                                .padding(20.dp)

                                .clickable {
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse(articleData?.url ?: "")
                                    }
                                    context.startActivity(intent)

                                }
                        )
                        Spacer(Modifier.height(24.dp))


                        AsyncImage(
                            model = articleData?.urlToImage,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .clip(RoundedCornerShape(32.dp))
                        )


                    }
                }
            }
        }

    }


}


@Composable
fun CategoryRowList(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val bold = if (isSelected) {
        FontWeight.ExtraBold
    } else {
        FontWeight.Normal
    }

    val decoration = if (isSelected) {
        TextDecoration.Underline
    } else {
        TextDecoration.None
    }

    Text(
        text, fontSize = 20.sp, modifier = Modifier.clickable {
            onClick()
        }, style = TextStyle(fontWeight = bold, textDecoration = decoration, fontFamily = inter)

    )
}

