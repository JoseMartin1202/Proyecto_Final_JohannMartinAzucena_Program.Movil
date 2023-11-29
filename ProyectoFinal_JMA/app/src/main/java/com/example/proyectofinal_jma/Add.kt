package com.example.proyectofinal_jma

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework
import com.example.proyectofinal_jma.navigation.AppScreens
import com.example.proyectofinal_jma.sizeScreen.WindowInfo
import com.example.proyectofinal_jma.sizeScreen.rememberWindowInfo
import com.example.proyectofinal_jma.ui.theme.Shapes
import com.example.proyectofinal_jma.viewModel.AppViewModelProvider
import com.example.proyectofinal_jma.viewModel.NoteDetails
import com.example.proyectofinal_jma.viewModel.NoteDetailsEdit
import com.example.proyectofinal_jma.viewModel.NoteEntryViewModel
import com.example.proyectofinal_jma.viewModel.NoteUiState
import com.example.proyectofinal_jma.viewModel.PhotoVideoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.coroutines.launch


@Composable
fun Add(
    contentPadding: PaddingValues,
    viewModel: NoteEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
){
    LazyColumn(
        contentPadding=contentPadding,
        modifier = Modifier.padding(top = 10.dp, start = 8.dp, end = 8.dp )){
        items(DataSourceNotesOrHomework.text){
            NoteEntryBody(
                noteUiState = viewModel.noteUiState,
                onNoteValueChange = viewModel::updateUiState,
                viewModelPhoto=viewModel)
        }
    }
}

@Composable
fun NoteEntryBody(
    noteUiState: NoteUiState,
    onNoteValueChange: (NoteDetails) -> Unit,
    viewModelPhoto: NoteEntryViewModel,
) {
    NoteInputForm(
        noteDetails = noteUiState.noteDetails,
        onValueChange =  onNoteValueChange,
        viewModel = viewModelPhoto)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInputForm(
    noteDetails: NoteDetails,
    onValueChange: (NoteDetails) -> Unit = {},
    viewModel: NoteEntryViewModel,
) {
    TextField(
        value = noteDetails.contenido,
        onValueChange = {
            onValueChange(noteDetails.copy(contenido = it))
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent),
        singleLine= false,
        textStyle = MaterialTheme.typography.bodyMedium)
    viewImages(viewModel = viewModel)
    viewVideos(viewModel = viewModel)
    viewAudios(viewModel = viewModel)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteHomework(
    modifier:  Modifier= Modifier,
    navController: NavController,
    viewModel: NoteEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val windowsSize= rememberWindowInfo()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_4)),
        topBar = {
            if(windowsSize.screenWindthInfo is WindowInfo.WindowType.Compact ){
                TopNoteEstructureCompact(modifier = modifier, navController = navController, viewModel = viewModel)
            }else if(windowsSize.screenWindthInfo is WindowInfo.WindowType.Medium){
                TopNoteEstructureMedium(modifier = modifier, navController = navController, viewModel = viewModel)
            }else{
                TopNoteEstructureExpanded(modifier = modifier, navController = navController, viewModel = viewModel)
            }
        },
        bottomBar = {
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Card (
                    modifier = modifier
                        .padding(
                            bottom = dimensionResource(id = R.dimen.padding_anchor_16)
                        )
                        .clip(Shapes.small)
                        .width(250.dp)
                        .align(Alignment.CenterVertically),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
                ){
                    Row (
                        modifier = modifier
                            .padding(dimensionResource(id = R.dimen.padding_4))
                            .fillMaxWidth()
                    ){
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier
                                .padding(
                                    end = dimensionResource(id = R.dimen.padding_8)
                                )
                                .weight(.33f)
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
                            modifier = modifier
                                .padding(
                                    start = dimensionResource(id = R.dimen.padding_8),
                                    end = dimensionResource(id = R.dimen.padding_8)
                                )
                                .weight(.33f)
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
                            modifier = modifier.weight(.33f)
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
        Add(contentPadding = it,viewModel)
    }
}

@Composable
fun TitleNoteEntryBody(
    noteUiState: NoteUiState,
    onNoteValueChange: (NoteDetails) -> Unit
) {
    TitleTextNote(
        noteDetails = noteUiState.noteDetails,
        onValueChange =  onNoteValueChange)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleTextNote(
    noteDetails: NoteDetails,
    onValueChange: (NoteDetails) -> Unit = {}
) {
    TextField(
        value = noteDetails.titulo,
        onValueChange = {
            onValueChange(noteDetails.copy(titulo = it))
        },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNoteEstructureCompact(
    modifier: Modifier,
    navController: NavController,
    viewModel: NoteEntryViewModel
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
                            viewModel.saveNote()
                            navController.navigate(route = AppScreens.MainScreen.route)
                            Toast.makeText(message,"Nota agregada",Toast.LENGTH_SHORT).show()
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
                TitleNoteEntryBody(noteUiState = viewModel.noteUiState, onNoteValueChange = viewModel::updateUiState)
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
            Column{
                Button(
                    onClick = {viewModel.updateShowCancel(true) },
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
                    MyDialog(
                        show = viewModel.showCancel,
                        onDismiss = { viewModel.updateShowCancel(false) },
                        onConfirm = {
                            navController.navigate(route = AppScreens.MainScreen.route)
                        },
                        titulo = stringResource(id = R.string.cancelarRegistro),
                        text = stringResource(id = R.string.cancelarRegistrotext)
                    )
                }
            }
        }
        Spacer(Modifier.height(dimensionResource(id = R.dimen.padding_4)))
        Row(modifier = modifier
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier =modifier.weight(.65f)
            ) {
                Row ( modifier =modifier.weight(.25f)){
                    ImageCapture(viewModel = viewModel, modifier = modifier)
                }
                Row ( modifier =modifier.weight(.25f)){
                    VideoCapture(viewModel = viewModel, modifier = modifier)
                }
                Row ( modifier =modifier.weight(.25f)){
                    AudioCapture(viewModel = viewModel, modifier = modifier)
                }
                Row ( modifier =modifier.weight(.25f)){
                    Recordatorio(viewModel = viewModel, modifier = modifier)
                }
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
            Column(
                modifier =modifier.weight(.35f)
            ) {
                ExposedDropdownMenuBox(
                    modifier = modifier
                        .padding(top = 4.dp, end = 8.dp)
                        .fillMaxWidth(),
                    expanded = viewModel.isExpanded,
                    onExpandedChange = {viewModel.updateIsExpanded(it)}
                ) {
                    TextField(
                        value = viewModel.optionNote,
                        onValueChange = {},
                        readOnly = true,
                        label ={ Text(stringResource(id = R.string.tipo))},
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
                                viewModel.updateOptionNote(text)
                                viewModel.isExpanded=false
                                viewModel.updateRecordatorios(false)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text2) },
                            onClick = {
                                viewModel.updateOptionNote(text2)
                                viewModel.isExpanded=false
                                viewModel.updateRecordatorios(true)
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
fun TopNoteEstructureMedium(
    modifier: Modifier,
    navController: NavController,
    viewModel: NoteEntryViewModel,
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
                            viewModel.saveNote()
                            navController.navigate(route = AppScreens.MainScreen.route)
                            Toast.makeText(message,"Nota agregada",Toast.LENGTH_SHORT).show()
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
                TitleNoteEntryBody(noteUiState = viewModel.noteUiState, onNoteValueChange = viewModel::updateUiState)
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_2)))
            Column{
                Button(
                    onClick = { viewModel.updateShowCancel(true)  },
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
                    MyDialog(
                        show = viewModel.showCancel,
                        onDismiss = { viewModel.updateShowCancel(false) },
                        onConfirm = {
                            navController.navigate(route = AppScreens.MainScreen.route)
                        },
                        titulo = stringResource(id = R.string.cancelarRegistro),
                        text = stringResource(id = R.string.cancelarRegistrotext)
                    )
                }
            }
        }
        //segunda fila
        Spacer(Modifier.height(dimensionResource(id = R.dimen.padding_4)))
        Row(modifier = modifier
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier =modifier.weight(.65f)
            ) {
                Button(
                    onClick = { },
                    modifier = modifier
                        .weight(.25f),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.gallery),
                        contentDescription = null,
                        modifier = modifier
                            .aspectRatio(1f),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Button(
                    onClick = { },
                    modifier = modifier
                        .weight(.25f),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.video),
                        contentDescription = null,
                        modifier = modifier
                            .aspectRatio(1f),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Button(
                    onClick = { },
                    modifier = modifier
                        .weight(.25f),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.audio),
                        contentDescription = null,
                        modifier = modifier
                            .aspectRatio(1f),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Button(
                    onClick = { },
                    modifier = modifier
                        .weight(.25f),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.file),
                        contentDescription = null,
                        modifier = modifier
                            .aspectRatio(1f),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
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
                    onExpandedChange = {viewModel.updateIsExpanded(it)}
                ) {
                    TextField(
                        value = viewModel.optionNote,
                        onValueChange = {},
                        readOnly = true,
                        label ={ Text(stringResource(id = R.string.tipo))},
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
                                viewModel.updateOptionNote(text)
                                viewModel.isExpanded=false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text2) },
                            onClick = {
                                viewModel.updateOptionNote(text2)
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
fun TopNoteEstructureExpanded(
    modifier: Modifier,
    navController: NavController,
    viewModel: NoteEntryViewModel
    ){
    val coroutineScope=rememberCoroutineScope()
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
                            viewModel.saveNote()
                            navController.navigate(route = AppScreens.MainScreen.route)
                            Toast.makeText(message,"Nota agregada",Toast.LENGTH_SHORT).show()
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
                modifier =modifier.weight(.45f)
            ) {
                TitleNoteEntryBody(noteUiState = viewModel.noteUiState, onNoteValueChange = viewModel::updateUiState)
            }
            Column (
                modifier =modifier.weight(.25f),
                verticalArrangement = Arrangement.Center
            ){
                Row {
                    Button(
                        onClick = { },
                        modifier = modifier
                            .weight(.25f),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.gallery),
                            contentDescription = null,
                            modifier = modifier
                                .aspectRatio(1f),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Button(
                        onClick = {},
                        modifier = modifier
                            .weight(.25f),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.video),
                            contentDescription = null,
                            modifier = modifier
                                .aspectRatio(1f),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Button(
                        onClick = { },
                        modifier = modifier
                            .weight(.25f),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.audio),
                            contentDescription = null,
                            modifier = modifier
                                .aspectRatio(1f),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Button(
                        onClick = { },
                        modifier = modifier
                            .weight(.25f),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.file),
                            contentDescription = null,
                            modifier = modifier
                                .aspectRatio(1f),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            Column(
                modifier =modifier.weight(.14f)
            ) {
                ExposedDropdownMenuBox(
                    modifier = modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    expanded = viewModel.isExpanded,
                    onExpandedChange = {viewModel.updateIsExpanded(it)}
                ) {
                    TextField(
                        value = viewModel.optionNote,
                        onValueChange = {},
                        readOnly = true,
                        label ={ Text(stringResource(id = R.string.tipo))},
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
                                viewModel.updateOptionNote(text)
                                viewModel.isExpanded=false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text2) },
                            onClick = {
                                viewModel.updateOptionNote(text2)
                                viewModel.isExpanded=false
                            }
                        )
                    }
                }
            }
            Column {
                Button(
                    onClick = { viewModel.updateShowCancel(true)  },
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
                    MyDialog(
                        show = viewModel.showCancel,
                        onDismiss = { viewModel.updateShowCancel(false) },
                        onConfirm = {
                            navController.navigate(route = AppScreens.MainScreen.route)
                        },
                        titulo = stringResource(id = R.string.cancelarRegistro),
                        text = stringResource(id = R.string.cancelarRegistrotext)
                    )
                }
            }
        }
}

@Composable
fun ImageCapture(
    viewModel: NoteEntryViewModel,
    modifier:Modifier
){
    val context = LocalContext.current
    var uri=ComposeFileProvider.getImageUri(context)
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            viewModel.updatehasImage(success)
            viewModel.updateImageUri(uri)
            viewModel.updateUrisList(uri)
        }
    )
    Button(
        onClick = {
            uri = ComposeFileProvider.getImageUri(context)
            cameraLauncher.launch(uri)
        },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ){
        Icon(
            painter = painterResource(id = R.drawable.gallery),
            contentDescription = null,
            modifier = modifier
                .aspectRatio(1f),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun VideoCapture(
    viewModel: NoteEntryViewModel,
    modifier:Modifier
){
    val context = LocalContext.current
    var uri=ComposeFileProvider.getVideoUri(context)
    val videoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo(),
        onResult = { success ->
            viewModel.updatehasVideo(success)
            viewModel.updateVideoUri(uri)
            viewModel.updateUrisVideosList(uri)
        }
    )
    Button(
        onClick = {
            uri = ComposeFileProvider.getVideoUri(context)
            videoLauncher.launch(uri)
        },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ){
        Icon(
            painter = painterResource(id = R.drawable.video),
            contentDescription = null,
            modifier = modifier
                .aspectRatio(1f),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AudioCapture(
    viewModel: NoteEntryViewModel,
    modifier:Modifier
){
    val context = LocalContext.current
    val recordAudioPermissionState = rememberPermissionState(
        Manifest.permission.RECORD_AUDIO
    )
    Button(
        onClick = {

        },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ){
        Icon(
            painter = painterResource(id = R.drawable.audio),
            contentDescription = null,
            modifier = modifier
                .aspectRatio(1f),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun filesCapture(
    viewModel: NoteEntryViewModel,
    modifier: Modifier
){
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            viewModel.updatehasImage(uri != null)
            if(viewModel.hasImage){
                viewModel.updateImageUri(uri)
                viewModel.updateUrisList(uri)
            }
        }
    )
    Button(
        onClick = {
            imagePicker.launch("image/*")
        },
        contentPadding = PaddingValues(0.dp),
        modifier = modifier.padding(end = 5.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.file), contentDescription ="" )
    }
}

@Composable
fun Recordatorio(
    viewModel: NoteEntryViewModel,
    modifier:Modifier
){
    Button(
        onClick = {

        },
        enabled=viewModel.recordatorios,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ){
        if(viewModel.recordatorios){
            Icon(
                painter = painterResource(id = R.drawable.clock),
                contentDescription = null,
                modifier = modifier
                    .aspectRatio(1f),
                tint = MaterialTheme.colorScheme.primary
            )
        }else{
            Icon(
                painter = painterResource(id = R.drawable.clock),
                contentDescription = null,
                modifier = modifier
                    .aspectRatio(1f),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun viewImages(
    viewModel: NoteEntryViewModel,
    modifier:Modifier=Modifier
) {
    Row(
        modifier= modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column {
            Row(
                modifier=modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.imagenes)+viewModel.cantidad,
                    modifier=modifier.weight(.3f))
                Row (
                    modifier=modifier.weight(.7f),
                    horizontalArrangement = Arrangement.End
                ){
                    filesCapture(viewModel = viewModel, modifier = modifier)
                    Button(
                        enabled = viewModel.cantidad!=0,
                        onClick = {
                            viewModel.deleteLastUri()
                        },
                        contentPadding = PaddingValues(10.dp)) {
                        Text(text = stringResource(id = R.string.eliminarUltimafoto))
                    }
                }
            }
            if (viewModel.hasImage){
                LazyRow(modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)) {
                    items(viewModel.urislist.toList()) { uri ->
                        Surface(
                            onClick = {
                                viewModel.updateMostrarImagen(true)
                            },
                            modifier = modifier
                                .size(width = 100.dp, height = 120.dp)
                        ){
                            AsyncImage(
                                model = uri,
                                modifier = Modifier.fillMaxWidth(),
                                contentDescription = "Selected image",
                            )
                        }
                        mostrarImagen(viewModel = viewModel, uri = uri)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun viewVideos(
    viewModel: NoteEntryViewModel,
    modifier:Modifier=Modifier
) {
    Row(
        modifier= modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column {
            Row(
                modifier=modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.videos)+viewModel.cantidadVideos,
                    modifier=modifier.weight(.4f))
                Row (
                    modifier=modifier.weight(.6f),
                    horizontalArrangement = Arrangement.End
                ){
                    Button(
                        enabled = viewModel.cantidadVideos!=0,
                        onClick = {
                            viewModel.deleteLastUriVideos()
                        },
                        contentPadding = PaddingValues(10.dp)) {
                        Text(text = stringResource(id = R.string.eliminarUltimoVideo))
                    }
                }
            }
            if (viewModel.hasVideo){
                LazyRow(modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)) {
                    items(viewModel.urisVideoslist.toList()) { uri ->
                        Surface(
                            onClick = {
                                viewModel.updateMostrarVideo(true)
                            },
                            modifier = modifier
                                .size(width = 100.dp, height = 120.dp)
                        ){
                            Icon(painter = painterResource(id = R.drawable.video_logo), contentDescription = "")
                        }
                        mostrarVideo(viewModel = viewModel, uri = uri)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun viewAudios(
    viewModel: NoteEntryViewModel,
    modifier:Modifier=Modifier
) {
    Row(
        modifier= modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column {
            Row(
                modifier=modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.audios)+viewModel.cantidadAudios,
                    modifier=modifier.weight(.4f))
                Row (
                    modifier=modifier.weight(.6f),
                    horizontalArrangement = Arrangement.End
                ){
                    Button(
                        enabled = viewModel.cantidadAudios!=0,
                        onClick = {
                            viewModel.deleteLastUriVideos()
                        },
                        contentPadding = PaddingValues(10.dp)) {
                        Text(text = stringResource(id = R.string.eliminarUltimoAudio))
                    }
                }
            }
            if (viewModel.hasAudio){
                LazyRow(modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)) {
                    items(viewModel.urisAudioslist.toList()) { uri ->
                        Surface(
                            onClick = {  },
                            modifier = modifier
                                .size(width = 100.dp, height = 120.dp)
                        ){
                           
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun mostrarImagen(
    viewModel: NoteEntryViewModel,
    uri: Uri?
){
    if(viewModel.mostrarImagen){
        Dialog(
            properties = DialogProperties(dismissOnClickOutside = true),
            onDismissRequest = { viewModel.updateMostrarImagen(false) }
        ) {
            AsyncImage(
                model = uri,
                modifier = Modifier.fillMaxSize(.9f),
                contentDescription = "Amplied image",
            )
        }
    }
}

@Composable
fun mostrarVideo(
    viewModel: NoteEntryViewModel,
    uri: Uri
){
    if(viewModel.mostrarVideo){
        Dialog(
            properties = DialogProperties(dismissOnClickOutside = true),
            onDismissRequest = { viewModel.updateMostrarVideo(false) }
        ) {
            val context = LocalContext.current
            VideoPlayer(videoUri = uri, context)
        }
    }
}

@Composable
fun VideoPlayer(videoUri: Uri, context:Context) {
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
        }
    }
    // Usar disposableEffect para liberar el exoPlayer cuando el composable se elimina del árbol de composición
    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release() // Liberar el exoPlayer
        }
    }
    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier.fillMaxWidth(.8f)
    )
}
