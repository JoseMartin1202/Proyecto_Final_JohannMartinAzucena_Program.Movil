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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework.cardsHomeworks
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework.cardsNotes
import com.example.proyectofinal_jma.model.Homework
import com.example.proyectofinal_jma.model.Note
import com.example.proyectofinal_jma.navigation.AppNavigation
import com.example.proyectofinal_jma.navigation.AppScreens
import com.example.proyectofinal_jma.sizeScreen.WindowInfo
import com.example.proyectofinal_jma.sizeScreen.rememberWindowInfo
import com.example.proyectofinal_jma.ui.theme.ProyectoFinal_JMATheme
import com.example.proyectofinal_jma.ui.theme.Shapes
import com.example.proyectofinal_jma.viewModel.AppViewModelProvider
import com.example.proyectofinal_jma.viewModel.NoteEntryViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinal_JMATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun NoteCard(note: Note, modifier: Modifier= Modifier){
    Card (modifier = modifier.padding(dimensionResource(id = R.dimen.padding_4))){
        Row (
            modifier= modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_4))
                .padding(end = dimensionResource(id = R.dimen.padding_4))
                .sizeIn(minHeight = dimensionResource(id = R.dimen.anchor_64))
        ){
            Box{
                Image(
                    painter = painterResource(id = note.miniature),
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
                    text = stringResource(id = note.titleCard),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier=modifier
                        .padding( top = dimensionResource(id = R.dimen.padding_8)))
                Text(
                    text = stringResource(id = note.descriptionCard),
                    style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_8)))
            Column(horizontalAlignment = Alignment.End){
                Text(
                    text = stringResource(id = note.dateCard),
                    style = MaterialTheme.typography.bodySmall)
                Box(
                    modifier= modifier.padding(top = dimensionResource(id = R.dimen.padding_anchor_16))
                ){
                    Icon(
                        painter = painterResource(id = note.favoriteImage),
                        contentDescription =null,
                        modifier = modifier
                            .size(
                                width = dimensionResource(id = R.dimen.padding_anchor_24),
                                height = dimensionResource(id = R.dimen.padding_anchor_24)
                            )
                            .aspectRatio(1f),
                        tint = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Composable
fun HomeworkCard(homework: Homework, modifier: Modifier= Modifier){
    Card (modifier = modifier.padding(dimensionResource(id = R.dimen.padding_4))){
        Row (
            modifier= modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_4))
                .padding(end = dimensionResource(id = R.dimen.padding_4))
                .sizeIn(minHeight = dimensionResource(id = R.dimen.anchor_64))
        ){
            Box{
                Image(
                    painter = painterResource(id = homework.miniature),
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
                    text = stringResource(id = homework.titleCard),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier=modifier
                        .padding( top = dimensionResource(id = R.dimen.padding_8)))
                Text(
                    text = stringResource(id = homework.descriptionCard),
                    style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_8)))
            Column(horizontalAlignment = Alignment.End){
                Text(
                    text = stringResource(id = homework.dateCard),
                    style = MaterialTheme.typography.bodySmall)
                Row (modifier=modifier.padding(top = dimensionResource(id = R.dimen.padding_anchor_16))){
                    Icon(
                        painter = painterResource(id = homework.favoriteImage),
                        contentDescription =null,
                        modifier = modifier
                            .size(
                                width = dimensionResource(id = R.dimen.padding_anchor_24),
                                height = dimensionResource(id = R.dimen.padding_anchor_24)
                            )
                            .aspectRatio(1f),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        painter = painterResource(id = homework.reminderImage),
                        contentDescription =null,
                        modifier = modifier
                            .size(
                                width = dimensionResource(id = R.dimen.padding_anchor_24),
                                height = dimensionResource(id = R.dimen.padding_anchor_24)
                            )
                            .aspectRatio(1f),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotePreview() {
    ProyectoFinal_JMATheme {
       NoteCard(Note(R.drawable.image,R.string.title,R.string.notaDescripcion,R.string.date,R.drawable.star))
    }
}
@Preview(showBackground = true)
@Composable
fun HomeworkPreview() {
    ProyectoFinal_JMATheme {
        HomeworkCard(Homework(R.drawable.image,R.string.title,R.string.homeworkDescription,R.string.date,R.drawable.star,R.drawable.bell_outlined))
    }
}

@Composable
fun ListElements(
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val windowsSize= rememberWindowInfo()
    if(windowsSize.screenWindthInfo is WindowInfo.WindowType.Compact){
        LazyColumn(
            contentPadding=contentPadding,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_4))){
            items(cardsHomeworks){
                HomeworkCard(homework= it)
            }
            items(cardsNotes){
                NoteCard(note= it)
            }
        }
    }else if (windowsSize.screenWindthInfo is WindowInfo.WindowType.Medium){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding=contentPadding,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_8)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_8))
        ){
            itemsIndexed(cardsHomeworks){ id,note->
                HomeworkCard(homework= note)
            }
            itemsIndexed(cardsNotes){ id,note->
                NoteCard(note= note)
            }
        }
    }else{
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding=contentPadding,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_8)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_8))
        ){
            itemsIndexed(cardsHomeworks){ id,note->
                HomeworkCard(homework= note)
            }
            itemsIndexed(cardsNotes){ id,note->
                NoteCard(note= note)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    modifier: Modifier= Modifier,
    navController: NavController,
    viewModel: NoteEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_4)),
        topBar = {
            Row(
                verticalAlignment = CenterVertically,
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
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription =null,
                        modifier = modifier
                            .aspectRatio(1f),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                TextField(
                    value = viewModel.textSearch,
                    onValueChange = {viewModel.updateTextSearch(it)},
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .height(50.dp)
                        .fillMaxWidth(),
                    trailingIcon = { Icon(
                        painter = painterResource(id = R.drawable.search) ,
                        contentDescription = null)},
                    shape = Shapes.large,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium)

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
                        .align(CenterVertically),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
                ){
                    Row (
                        modifier = modifier
                            .padding(dimensionResource(id = R.dimen.padding_4))
                            .fillMaxWidth(),
                        verticalAlignment = CenterVertically,
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
                                onClick = {
                                    navController.navigate(route = AppScreens.TrashScreen.route)
                                },
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
        ListElements(contentPadding = it)
    }
}
