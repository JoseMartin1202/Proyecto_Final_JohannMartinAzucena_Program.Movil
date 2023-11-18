package com.example.proyectofinal_jma

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework
import com.example.proyectofinal_jma.data.NotaEntity
import com.example.proyectofinal_jma.navigation.AppScreens
import com.example.proyectofinal_jma.sizeScreen.WindowInfo
import com.example.proyectofinal_jma.sizeScreen.rememberWindowInfo
import com.example.proyectofinal_jma.ui.theme.ProyectoFinal_JMATheme
import com.example.proyectofinal_jma.ui.theme.Shapes
import com.example.proyectofinal_jma.viewModel.AppViewModelProvider
import com.example.proyectofinal_jma.viewModel.NoteDetails
import com.example.proyectofinal_jma.viewModel.NoteEditViewModel
import com.example.proyectofinal_jma.viewModel.NoteEntryViewModel
import com.example.proyectofinal_jma.viewModel.NoteUiState
import com.example.proyectofinal_jma.viewModel.toNoteDetails
import kotlinx.coroutines.launch

@Composable
fun Edit(
    contentPadding: PaddingValues,
    viewModel: NoteEditViewModel
){
    LazyColumn(
        contentPadding=contentPadding,
        modifier = Modifier.padding(top = 10.dp, start = 8.dp, end = 8.dp )){
        items(DataSourceNotesOrHomework.text){
            NoteEntryBody(
                noteUiState = viewModel.noteUiStateEdit,
                onNoteValueChange = viewModel::updateUiStateEdit)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteHomework(
    modifier:  Modifier= Modifier,
    navController: NavController,
    notaEntity: NotaEntity,
    viewModel: NoteEditViewModel= viewModel(factory = AppViewModelProvider.Factory)
) {
    viewModel.updateUiStateEdit(notaEntity.toNoteDetails())
    val windowsSize= rememberWindowInfo()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_4)),
        topBar = {
            if(windowsSize.screenWindthInfo is WindowInfo.WindowType.Compact ){
                TopNoteEstructureCompactEdit(modifier = modifier, navController = navController, viewModel = viewModel)
            }else if(windowsSize.screenWindthInfo is WindowInfo.WindowType.Medium){
                TopNoteEstructureMediumEdit(modifier = modifier, navController = navController, viewModel = viewModel)
            }else{
                TopNoteEstructureExpandedEdit(modifier = modifier, navController = navController, viewModel = viewModel)
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
        Edit(contentPadding = it, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNoteEstructureCompactEdit(
    modifier: Modifier,
    navController: NavController,
    viewModel: NoteEditViewModel,
){
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.Center
        ){
            Column{
                val message= LocalContext.current.applicationContext
                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.updateNote()
                            navController.navigate(route = AppScreens.MainScreen.route)
                            Toast.makeText(message,"Nota actualizada", Toast.LENGTH_SHORT).show()
                        }
                    },
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
                TitleNoteEntryBody(noteUiState = viewModel.noteUiStateEdit, onNoteValueChange = viewModel::updateUiStateEdit)
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
            .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier =modifier.weight(.135f)
            ) {
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
                modifier =modifier.weight(.38f)
            ){
                ExposedDropdownMenuBox(
                    modifier = modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    expanded = viewModel.isExpanded2,
                    onExpandedChange = {viewModel.updateIsExpandend2Edit(it)}
                ) {
                    TextField(
                        value = viewModel.sizeText,
                        onValueChange = {},
                        readOnly = true,
                        label ={ Text(stringResource(id = R.string.fuente))},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                        modifier = Modifier.menuAnchor(),
                    )
                    ExposedDropdownMenu(
                        expanded = viewModel.isExpanded2,
                        onDismissRequest = {viewModel.isExpanded2=false}
                    ) {
                        var text=stringResource(id = R.string.normal)
                        var text2=stringResource(id = R.string.mediana)
                        var text3=stringResource(id = R.string.grande)
                        DropdownMenuItem(
                            text = { Text(text) },
                            onClick = {
                                viewModel.updatesizeTextEdit(text)
                                viewModel.isExpanded2=false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text2) },
                            onClick = {
                                viewModel.updatesizeTextEdit(text2)
                                viewModel.isExpanded2=false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text3) },
                            onClick = {
                                viewModel.updatesizeTextEdit(text3)
                                viewModel.isExpanded2=false
                            }
                        )
                    }
                }
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
            Column(
                modifier =modifier.weight(.135f)
            ){
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
                modifier =modifier.weight(.35f)
            ) {
                ExposedDropdownMenuBox(
                    modifier = modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    expanded = viewModel.isExpanded,
                    onExpandedChange = {viewModel.updateIsExpandedEdit(it)}
                ) {
                    TextField(
                        value = viewModel.optionNote,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = viewModel.isExpanded,
                        onDismissRequest = {viewModel.isExpanded=false}
                    ) {
                        var text=stringResource(id = R.string.nota)
                        var text2=stringResource(id = R.string.tarea)
                        DropdownMenuItem(
                            text = { Text(text) },
                            onClick = {
                                viewModel.updateOptionNoteEdit(text)
                                viewModel.isExpanded=false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text2) },
                            onClick = {
                                viewModel.updateOptionNoteEdit(text2)
                                viewModel.isExpanded=false
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNoteEstructureMediumEdit(
    modifier: Modifier,
    navController: NavController,
    viewModel: NoteEditViewModel= viewModel(factory = AppViewModelProvider.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.Center
        ){
            Column{
                val message= LocalContext.current.applicationContext
                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.updateNote()
                            navController.navigate(route = AppScreens.MainScreen.route)
                            Toast.makeText(message,"Nota actualizada", Toast.LENGTH_SHORT).show()
                        }
                    },
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
                TitleNoteEntryBody(noteUiState = viewModel.noteUiStateEdit, onNoteValueChange = viewModel::updateUiStateEdit)
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
        //segunda fila
        Spacer(Modifier.height(dimensionResource(id = R.dimen.padding_4)))
        Row(modifier = modifier
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background)
        ) {
            Column {
                Box {//Aproximadamente equivalen al 8% o 7% de la pantalla
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
                modifier =modifier.weight(.38f)
            ){
                ExposedDropdownMenuBox(
                    modifier = modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    expanded = viewModel.isExpanded2,
                    onExpandedChange = {viewModel.updateIsExpandend2Edit(it)}
                ) {
                    TextField(
                        value = viewModel.sizeText,
                        onValueChange = {},
                        readOnly = true,
                        label ={ Text(stringResource(id = R.string.fuente))},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                        modifier = Modifier.menuAnchor(),
                    )
                    ExposedDropdownMenu(
                        expanded = viewModel.isExpanded2,
                        onDismissRequest = {viewModel.isExpanded2=false}
                    ) {
                        var text=stringResource(id = R.string.normal)
                        var text2=stringResource(id = R.string.mediana)
                        var text3=stringResource(id = R.string.grande)
                        DropdownMenuItem(
                            text = { Text(text) },
                            onClick = {
                                viewModel.updatesizeTextEdit(text)
                                viewModel.isExpanded2=false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text2) },
                            onClick = {
                                viewModel.updatesizeTextEdit(text2)
                                viewModel.isExpanded2=false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text3) },
                            onClick = {
                                viewModel.updatesizeTextEdit(text3)
                                viewModel.isExpanded2=false
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
                modifier =modifier.weight(.35f)
            ) {
                ExposedDropdownMenuBox(
                    modifier = modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    expanded = viewModel.isExpanded,
                    onExpandedChange = {viewModel.updateIsExpandedEdit(it)}
                ) {
                    TextField(
                        value = viewModel.optionNote,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = viewModel.isExpanded,
                        onDismissRequest = {viewModel.isExpanded=false}
                    ) {
                        var text=stringResource(id = R.string.nota)
                        var text2=stringResource(id = R.string.tarea)
                        DropdownMenuItem(
                            text = { Text(text) },
                            onClick = {
                                viewModel.updateOptionNoteEdit(text)
                                viewModel.isExpanded=false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text2) },
                            onClick = {
                                viewModel.updateOptionNoteEdit(text2)
                                viewModel.isExpanded=false
                            }
                        )
                    }
                }
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_8)))
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNoteEstructureExpandedEdit(
    modifier: Modifier,
    navController: NavController,
    viewModel: NoteEditViewModel
){
    val coroutineScope= rememberCoroutineScope()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.Center
    ){
        Column{
            val message= LocalContext.current.applicationContext
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateNote()
                        navController.navigate(route = AppScreens.MainScreen.route)
                        Toast.makeText(message,"Nota actualizada", Toast.LENGTH_SHORT).show()
                    }
                },
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
        Column(
            modifier =modifier.weight(.4f)
        ) {
            TitleNoteEntryBody(noteUiState = viewModel.noteUiStateEdit, onNoteValueChange = viewModel::updateUiStateEdit)
        }
        Column (
            modifier =modifier.weight(.04f),
            verticalArrangement = Arrangement.Center
        ){
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
        //segunda  fila
        Column(
            modifier =modifier.weight(.14f)
        ) {
            ExposedDropdownMenuBox(
                modifier = modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                expanded = viewModel.isExpanded2,
                onExpandedChange = {viewModel.updateIsExpandend2Edit(it)}
            ) {
                TextField(
                    value = viewModel.sizeText,
                    onValueChange = {},
                    readOnly = true,
                    label ={ Text(stringResource(id = R.string.fuente))},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                    modifier = Modifier.menuAnchor(),
                )
                ExposedDropdownMenu(
                    expanded = viewModel.isExpanded2,
                    onDismissRequest = {viewModel.isExpanded2=false}
                ) {
                    var text=stringResource(id = R.string.normal)
                    var text2=stringResource(id = R.string.mediana)
                    var text3=stringResource(id = R.string.grande)
                    DropdownMenuItem(
                        text = { Text(text) },
                        onClick = {
                            viewModel.updatesizeTextEdit(text)
                            viewModel.isExpanded2=false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text2) },
                        onClick = {
                            viewModel.updatesizeTextEdit(text2)
                            viewModel.isExpanded2=false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text3) },
                        onClick = {
                            viewModel.updatesizeTextEdit(text3)
                            viewModel.isExpanded2=false
                        }
                    )
                }
            }
        }
        Column(
            modifier =modifier.weight(.05f)
        ) {
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
        Column(
            modifier =modifier.weight(.14f)
        ) {
            ExposedDropdownMenuBox(
                modifier = modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                expanded = viewModel.isExpanded,
                onExpandedChange = {viewModel.updateIsExpandedEdit(it)}
            ) {
                TextField(
                    value = viewModel.optionNote,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = viewModel.isExpanded,
                    onDismissRequest = {viewModel.isExpanded=false}
                ) {
                    var text=stringResource(id = R.string.nota)
                    var text2=stringResource(id = R.string.tarea)
                    DropdownMenuItem(
                        text = { Text(text) },
                        onClick = {
                            viewModel.updateOptionNoteEdit(text)
                            viewModel.isExpanded=false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text2) },
                        onClick = {
                            viewModel.updateOptionNoteEdit(text2)
                            viewModel.isExpanded=false
                        }
                    )
                }
            }
        }
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
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription =null,
                    modifier = modifier
                        .aspectRatio(1f),
                    tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}