package com.example.proyectofinal_jma

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework
import com.example.proyectofinal_jma.model.HomeworkNoteDone
import com.example.proyectofinal_jma.navigation.AppScreens
import com.example.proyectofinal_jma.ui.theme.ProyectoFinal_JMATheme
import com.example.proyectofinal_jma.ui.theme.Shapes

@Composable
fun CardTrash(homeworkNoteDone: HomeworkNoteDone, modifier: Modifier= Modifier){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = false ,
            onCheckedChange = {})
        Card (
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_4))
        ){
            Row (
                modifier= modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_4))
                    .padding(end = dimensionResource(id = R.dimen.padding_4))
                    .sizeIn(minHeight = dimensionResource(id = R.dimen.anchor_64))
            ){
                Box{
                    Image(
                        painter = painterResource(id = homeworkNoteDone.miniature),
                        contentDescription =null,
                        modifier = modifier
                            .size(
                                width = dimensionResource(id = R.dimen.anchor_64),
                                height = dimensionResource(id = R.dimen.anchor_64)
                            )
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = homeworkNoteDone.titleCard),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier=modifier
                            .padding( top = dimensionResource(id = R.dimen.padding_8)))
                    Text(
                        text = stringResource(id = homeworkNoteDone.descriptionCard),
                        style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_8)))
                Column(horizontalAlignment = Alignment.End){
                    Text(
                        text = stringResource(id = homeworkNoteDone.dateCard),
                        style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun TrashCards(
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding=contentPadding,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_4))
    ){

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Trash(
    modifier: Modifier= Modifier,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_4)),
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(end = 8.dp, start = 4.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Checkbox(
                    checked = false,
                    onCheckedChange ={} )
                Spacer(Modifier.width(1.dp))
                Text(
                    text = stringResource(id = R.string.todas),
                    style = MaterialTheme.typography.bodyLarge)
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .height(40.dp)
                            .width(60.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = null,
                            modifier = modifier
                                .aspectRatio(1f),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .height(40.dp)
                            .width(60.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.information),
                            contentDescription = null,
                            modifier = modifier
                                .aspectRatio(1f),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .height(40.dp)
                            .width(60.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.restore),
                            contentDescription = null,
                            modifier = modifier
                                .aspectRatio(1f),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
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
                        .align(Alignment.CenterVertically),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
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
                                onClick = {
                                    navController.navigate(route = AppScreens.SettingsScreen.route)
                                },
                                modifier = modifier
                                    .height(40.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.settings),
                                    contentDescription =null,
                                    modifier = modifier
                                        .aspectRatio(1f),
                                    tint = MaterialTheme.colorScheme.secondary
                                )
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
                                onClick = { },
                                modifier = modifier
                                    .height(40.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.trash),
                                    contentDescription =null,
                                    modifier = modifier
                                        .aspectRatio(1f),
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                            Text(
                                text = stringResource(id = R.string.papelera),
                                style = MaterialTheme.typography.bodyMedium)
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.padding(
                                start = dimensionResource(id = R.dimen.padding_8),
                                end = dimensionResource(id = R.dimen.padding_8))
                        ){
                            Button(
                                onClick = {
                                    navController.navigate(route = AppScreens.AddScreen.route)
                                },
                                modifier = modifier
                                    .height(60.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.plus),
                                    contentDescription = null,
                                    modifier = modifier
                                        .aspectRatio(1f)
                                )
                            }
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.padding(
                                start = dimensionResource(id = R.dimen.padding_8),
                                end = dimensionResource(id = R.dimen.padding_8))
                        ){
                            Button(
                                onClick = {
                                    navController.navigate(route = AppScreens.DoneScreen.route)
                                },
                                modifier = modifier
                                    .height(40.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.done),
                                    contentDescription =null,
                                    modifier = modifier
                                        .aspectRatio(1f),
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                            Text(
                                text = stringResource(id = R.string.hecho),
                                style = MaterialTheme.typography.bodyMedium)
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Button(
                                onClick = {
                                    navController.navigate(route = AppScreens.MainScreen.route)
                                },
                                modifier = modifier
                                    .height(40.dp)
                                    .width(60.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription =null,
                                    modifier = modifier
                                        .aspectRatio(1f),
                                    tint = MaterialTheme.colorScheme.secondary
                                )
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
        TrashCards(contentPadding = it)
    }
}