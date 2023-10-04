package com.example.proyectofinal_jma

import android.os.Bundle
import android.widget.Spinner
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework.text
import com.example.proyectofinal_jma.model.Content
import com.example.proyectofinal_jma.ui.theme.ProyectoFinal_JMATheme
import com.example.proyectofinal_jma.ui.theme.Shapes

class Add : ComponentActivity() {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(contentPadding: PaddingValues = PaddingValues(0.dp)){
    LazyColumn(
        contentPadding=contentPadding,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_4))){
        items(text){
            textCard(content = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textCard(content: Content){
    TextField(
        value = stringResource(content.text),
        onValueChange = {},
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
            .height(630.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent),
        textStyle = MaterialTheme.typography.bodyMedium)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddNoteHomework(modifier: Modifier = Modifier){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_4)),
        topBar = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(end = 8.dp, start = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .height(50.dp)
                            .width(50.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.check),
                            contentDescription =null,
                            modifier = modifier
                                .aspectRatio(1f),
                            contentScale = ContentScale.Crop)
                    }
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    TextField(
                        value = stringResource(id = R.string.titulo),
                        onValueChange = {},
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .height(50.dp),
                        shape = Shapes.large,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .height(50.dp)
                            .width(50.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.cancel),
                            contentDescription =null,
                            modifier = modifier
                                .aspectRatio(1f)
                                .height(50.dp)
                                .width(50.dp),
                            contentScale = ContentScale.Crop)
                    }
                }
                Spacer(Modifier.height(dimensionResource(id = R.dimen.padding_4)))
                Row(modifier = modifier
                    .height(50.dp)
                    .padding(end = 8.dp, start = 8.dp)) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.text_size),
                            contentDescription = null,
                            modifier = modifier
                                .aspectRatio(1f)
                                .height(50.dp)
                                .width(50.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    TextField(
                        value = stringResource(id = R.string.titulo),
                        onValueChange = {},
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .height(50.dp)
                            .width(100.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    Image(
                        painter = painterResource(id = R.drawable.gallery),
                        contentDescription = null,
                        modifier = modifier
                            .aspectRatio(1f)
                            .height(50.dp)
                            .width(50.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    ExposedDropdownMenuBox(
                        modifier = modifier.height(50.dp),
                        expanded = false,
                        onExpandedChange = {}
                    ) {
                        TextField(
                            value = stringResource(id = R.string.nota),
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                            modifier = Modifier.menuAnchor()
                        )
                    }
                }
            }
        },
        bottomBar = {
            Row (
                modifier = modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_8),
                    end = dimensionResource(id = R.dimen.padding_8)
                ),
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
                                end = dimensionResource(id = R.dimen.padding_8)
                            )
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
                                style = MaterialTheme.typography.bodyLarge)
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.padding(
                                end = dimensionResource(id = R.dimen.padding_8)
                            )
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
                                style = MaterialTheme.typography.bodyLarge)
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.padding(
                                start = dimensionResource(id = R.dimen.padding_8),
                                end = dimensionResource(id = R.dimen.padding_8)
                            )
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
                                end = dimensionResource(id = R.dimen.padding_8)
                            )
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
                                style = MaterialTheme.typography.bodyLarge)
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
                                style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }

        }
    ) {
        Add(contentPadding = it)
    }
}