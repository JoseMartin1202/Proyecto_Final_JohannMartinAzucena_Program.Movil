package com.example.proyectofinal_jma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectofinal_jma.data.Themes.themes
import com.example.proyectofinal_jma.model.Theme
import com.example.proyectofinal_jma.ui.theme.ProyectoFinal_JMATheme
import com.example.proyectofinal_jma.ui.theme.Shapes

class ThemesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinal_JMATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun ThemeCard( theme: Theme, modifier: Modifier= Modifier){
    Card (
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_4))
            .fillMaxWidth()
    ){
        Column (
           modifier = Modifier.fillMaxWidth(),
        ){
            Button(
                onClick = { /*TODO*/ },
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription =null,
                    modifier = modifier
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.Start
            ){
                Row {
                    Column {
                        Text(
                            text = stringResource(id = R.string.oscuro),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Left,
                            color = Color.Black)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = dimensionResource(id = R.dimen.padding_8)),
                        horizontalAlignment = Alignment.End
                    ) {
                        Box{
                            Image(
                                painter = painterResource(id = R.drawable.check),
                                contentDescription =null,
                                modifier = modifier
                                    .width(30.dp)
                                    .aspectRatio(1f),
                                contentScale = ContentScale.Crop)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun items(contentPadding: PaddingValues = PaddingValues(0.dp)){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding=contentPadding,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_4))
    ){
        this.items(themes){
            ThemeCard(theme = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ThemesList(modifier: Modifier= Modifier) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_4)),
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(end = 8.dp, start = 8.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { /*TODO*/ },
                    modifier = modifier
                        .height(40.dp)
                        .width(40.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription =null,
                        modifier = modifier
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop)
                }
                Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                Text(
                    text = stringResource(id = R.string.escogeTema),
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Left,
                    color = Color.Black)

            }
        },
        bottomBar = {
            Row (
                modifier = modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_8),
                    end = dimensionResource(id = R.dimen.padding_8)),
            ){
                Card (
                    modifier = modifier
                        .padding(
                            bottom = dimensionResource(id = R.dimen.padding_anchor_16)
                        )
                        .clip(Shapes.small)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ){
                    Row (
                        modifier = modifier
                            .padding(dimensionResource(id = R.dimen.padding_4))
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.padding(
                                end = dimensionResource(id = R.dimen.padding_8))
                        ){
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = modifier
                                    .height(40.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.settings),
                                    contentDescription =null,
                                    modifier = modifier
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Crop)
                            }
                            Text(
                                text = stringResource(id = R.string.ajustes),
                                style = MaterialTheme.typography.bodyMedium)
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.padding(
                                end = dimensionResource(id = R.dimen.padding_8))
                        ){
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = modifier
                                    .height(40.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription =null,
                                    modifier = modifier
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Crop)
                            }
                            Text(
                                text = stringResource(id = R.string.favoritos),
                                style = MaterialTheme.typography.bodyMedium)
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.padding(
                                start = dimensionResource(id = R.dimen.padding_8),
                                end = dimensionResource(id = R.dimen.padding_8))
                        ){
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = modifier
                                    .height(60.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.plus),
                                    contentDescription =null,
                                    modifier = modifier
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Crop)
                            }
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.padding(
                                start = dimensionResource(id = R.dimen.padding_8),
                                end = dimensionResource(id = R.dimen.padding_8))
                        ){
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = modifier
                                    .height(40.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.done),
                                    contentDescription =null,
                                    modifier = modifier
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Crop)
                            }
                            Text(
                                text = stringResource(id = R.string.hecho),
                                style = MaterialTheme.typography.bodyMedium)
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = modifier
                                    .height(40.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription =null,
                                    modifier = modifier
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Crop)
                            }
                            Text(
                                text = stringResource(id = R.string.principal),
                                style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    ) {
        items(contentPadding = it)
    }
}