package com.example.proyectofinal_jma

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework
import com.example.proyectofinal_jma.model.Content
import com.example.proyectofinal_jma.navigation.AppNavigation
import com.example.proyectofinal_jma.navigation.AppScreens
import com.example.proyectofinal_jma.ui.theme.ProyectoFinal_JMATheme
import com.example.proyectofinal_jma.ui.theme.Shapes
import com.example.proyectofinal_jma.viewModel.NoteViewModel

@Composable
fun Add(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    noteViewModel: NoteViewModel = viewModel()
){
    LazyColumn(
        contentPadding=contentPadding,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8))){
        items(DataSourceNotesOrHomework.text){
            TextCard(
                text = noteViewModel.textBodyNoteUser,
                newtext = { noteViewModel.updateBodyNote(it)}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextCard(
    text: String,
    newtext: (String) -> Unit
){
    TextField(
        value = text,
        onValueChange = newtext,
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
            .height(550.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent),
        singleLine= false,
        textStyle = MaterialTheme.typography.bodyMedium)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteHomework(
    modifier:  Modifier= Modifier,
    navController: NavController,
    noteViewModel: NoteViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_4)),
        topBar = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .padding(end = 8.dp, start = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Column {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = modifier
                                .height(55.dp)
                                .width(55.dp),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.check),
                                contentDescription =null,
                                modifier = modifier
                                    .aspectRatio(1f),
                                tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    Column(
                        modifier =modifier.weight(.5f)
                    ){
                        TextField(
                            value = noteViewModel.textTitleNoteUser,
                            onValueChange = {noteViewModel.updateTitleNote(it)},
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(),
                            shape = Shapes.large,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true,
                            label={ Text(text = stringResource(id = R.string.titulo)) },
                            textStyle = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    Column{
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = modifier
                                .height(55.dp)
                                .width(55.dp),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cancel),
                                contentDescription =null,
                                modifier = modifier
                                    .aspectRatio(1f),
                                tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
                Spacer(Modifier.height(dimensionResource(id = R.dimen.padding_4)))
                Row(modifier = modifier
                    .height(60.dp)
                    .padding(end = 8.dp, start = 8.dp)
                ) {
                    Column {
                        Box {
                            Icon(
                                painter = painterResource(id = R.drawable.text_size),
                                contentDescription = null,
                                modifier = modifier
                                    .aspectRatio(1f)
                                    .height(50.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    Column (
                        modifier =modifier.weight(.35f)
                    ){
                        ExposedDropdownMenuBox(
                            modifier = modifier
                                .padding(top = 4.dp),
                            expanded = noteViewModel.isExpanded2,
                            onExpandedChange = {noteViewModel.updateIsExpandend2(it)}
                        ) {
                            TextField(
                                value = noteViewModel.sizeText,
                                onValueChange = {},
                                readOnly = true,
                                label ={ Text(stringResource(id = R.string.fuente))},
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                                modifier = Modifier.menuAnchor(),

                            )
                            ExposedDropdownMenu(
                                expanded = noteViewModel.isExpanded2,
                                onDismissRequest = {noteViewModel.isExpanded2=false}
                            ) {
                                var text=stringResource(id = R.string.normal)
                                var text2=stringResource(id = R.string.mediana)
                                var text3=stringResource(id = R.string.grande)
                                DropdownMenuItem(
                                    text = { Text(text) },
                                    onClick = {
                                        noteViewModel.updatesizeText(text)
                                        noteViewModel.isExpanded2=false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text(text2) },
                                    onClick = {
                                        noteViewModel.updatesizeText(text2)
                                        noteViewModel.isExpanded2=false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text(text3) },
                                    onClick = {
                                        noteViewModel.updatesizeText(text3)
                                        noteViewModel.isExpanded2=false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    Column{
                        Icon(
                            painter = painterResource(id = R.drawable.gallery),
                            contentDescription = null,
                            modifier = modifier
                                .aspectRatio(1f)
                                .height(50.dp)
                                .width(50.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
                    Column(
                        modifier =modifier.weight(.3f)
                    ) {
                        ExposedDropdownMenuBox(
                            modifier = modifier
                                .padding(top = 4.dp),
                            expanded = noteViewModel.isExpanded,
                            onExpandedChange = {noteViewModel.updateIsExpanden(it)}
                        ) {
                            TextField(
                                value = noteViewModel.optionNote,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                                modifier = Modifier.menuAnchor()
                            )
                            ExposedDropdownMenu(
                                expanded = noteViewModel.isExpanded,
                                onDismissRequest = {noteViewModel.isExpanded=false}
                            ) {
                                var text=stringResource(id = R.string.nota)
                                var text2=stringResource(id = R.string.tarea)
                                DropdownMenuItem(
                                    text = { Text(text) },
                                    onClick = {
                                        noteViewModel.updateOptionNote(text)
                                        noteViewModel.isExpanded=false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text(text2) },
                                    onClick = {
                                        noteViewModel.updateOptionNote(text2)
                                        noteViewModel.isExpanded=false
                                    }
                                )
                            }
                        }
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
                                onClick = { },
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
        Add(contentPadding = it)
    }
}