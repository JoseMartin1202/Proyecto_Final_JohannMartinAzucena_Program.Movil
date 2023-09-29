package com.example.proyectofinal_jma.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class HomeworkOrNote(
    @DrawableRes val miniature:Int,
    @StringRes val titleCard:Int,
    @StringRes val descriptionCard:Int,
    @StringRes val dateCard:Int,
    @DrawableRes val favoriteImage:Int,
    @DrawableRes val  reminderImage:Int
)
