package com.example.newsdomemain.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsdomemain.R
import com.example.newsdomemain.data.model.Article


val jacques = FontFamily(
    Font(R.font.jacques)
)

val noticia = FontFamily(
    Font(R.font.noticia)
)

@Composable
fun NewsListItem(article: Article, onClick: () -> Unit) {
    Column(modifier = Modifier
        .wrapContentSize()
        .clickable { onClick() }) {

        Spacer(Modifier.height(30.dp))
        Text(
            article.title,
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 20.dp),
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = jacques,
                textAlign = TextAlign.Left,
                hyphens = Hyphens.Auto
            ), softWrap = true
        )
        Spacer(Modifier.height(8.dp))
        AsyncImage(
            model = article.urlToImage,
            contentDescription = null,
            modifier = Modifier.padding(20.dp), contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.height(8.dp))
        Text(
            article.description,
            modifier = Modifier.padding(horizontal = 20.dp),
            style = TextStyle(fontSize = 16.sp, fontFamily = noticia, hyphens = Hyphens.Auto),
            softWrap = true
        )
        Spacer(Modifier.height(30.dp))
        HorizontalDivider(thickness = 2.dp)

    }


}