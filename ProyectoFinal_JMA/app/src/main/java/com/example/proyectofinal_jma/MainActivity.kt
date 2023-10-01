package com.example.proyectofinal_jma

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework.cardsHomeworks
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework.cardsNotes
import com.example.proyectofinal_jma.model.Homework
import com.example.proyectofinal_jma.model.Note
import com.example.proyectofinal_jma.ui.theme.ProyectoFinal_JMATheme
import com.example.proyectofinal_jma.ui.theme.Shapes

class MainActivity : ComponentActivity() {
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
                    text = stringResource(id = R.string.titulo),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier=modifier
                        .padding( top = dimensionResource(id = R.dimen.padding_8)))
                Text(
                    text = stringResource(id = R.string.notaDescripcion),
                    style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_8)))
            Column(horizontalAlignment = Alignment.End){
                Text(
                    text = stringResource(id = R.string.date),
                    style = MaterialTheme.typography.bodySmall)
                Box(
                    modifier= modifier.padding(top = dimensionResource(id = R.dimen.padding_anchor_16))
                ){
                    Image(
                        painter = painterResource(id = note.favoriteImage),
                        contentDescription =null,
                        modifier = modifier
                            .size(
                                width = dimensionResource(id = R.dimen.padding_anchor_24),
                                height = dimensionResource(id = R.dimen.padding_anchor_24)
                            )
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop)
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
                    text = stringResource(id = R.string.titulo),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier=modifier
                        .padding( top = dimensionResource(id = R.dimen.padding_8)))
                Text(
                    text = stringResource(id = R.string.notaDescripcion),
                    style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_8)))
            Column(horizontalAlignment = Alignment.End){
                Text(
                    text = stringResource(id = R.string.date),
                    style = MaterialTheme.typography.bodySmall)
                Row (modifier=modifier.padding(top = dimensionResource(id = R.dimen.padding_anchor_16))){
                    Image(
                        painter = painterResource(id = homework.favoriteImage),
                        contentDescription =null,
                        modifier = modifier
                            .size(
                                width = dimensionResource(id = R.dimen.padding_anchor_24),
                                height = dimensionResource(id = R.dimen.padding_anchor_24)
                            )
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop)
                    Image(
                        painter = painterResource(id = homework.reminderImage),
                        contentDescription =null,
                        modifier = modifier
                            .size(
                                width = dimensionResource(id = R.dimen.padding_anchor_24),
                                height = dimensionResource(id = R.dimen.padding_anchor_24)
                            )
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop)
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
        HomeworkCard(Homework(R.drawable.image,R.string.title,R.string.notaDescripcion,R.string.date,R.drawable.star,R.drawable.bell_outlined))
    }
}

@Composable
fun ListElements(
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(contentPadding=contentPadding, modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_4))){
        items(cardsHomeworks){
            HomeworkCard(homework= it)
        }
        items(cardsNotes){
            NoteCard(note= it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun App(modifier: Modifier= Modifier) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(end = 8.dp)
            ){
                Box (modifier=modifier.wrapContentHeight()){
                    Image(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = null,
                        modifier = modifier
                            .height(45.dp)
                            .aspectRatio(1f)
                            .padding(start = dimensionResource(id = R.dimen.padding_8))
                    )
                }
                Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_8)))
                TextField(
                    value = stringResource(id = R.string.buscar),
                    onValueChange = {},
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
        }
    ) {
        ListElements(contentPadding = it)
    }
}
