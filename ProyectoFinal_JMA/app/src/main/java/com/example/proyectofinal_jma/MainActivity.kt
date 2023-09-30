package com.example.proyectofinal_jma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework.cardsHomeworks
import com.example.proyectofinal_jma.data.DataSourceNotesOrHomework.cardsNotes
import com.example.proyectofinal_jma.model.Homework
import com.example.proyectofinal_jma.model.Note
import com.example.proyectofinal_jma.ui.theme.ProyectoFinal_JMATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinal_JMATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListElements()
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

@Preview(showBackground = true)
@Composable
fun ListElements() {
    LazyColumn(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8))){
        items(cardsHomeworks){
            HomeworkCard(homework= it)
        }
        items(cardsNotes){
            NoteCard(note= it)
        }
    }
}

