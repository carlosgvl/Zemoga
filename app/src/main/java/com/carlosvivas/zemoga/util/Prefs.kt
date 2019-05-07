package com.carlosvivas.zemoga.util

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_FILENAME = "favorites"
    val POST_FAVORITE = "post_favorites"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var Favorite_post: Int
        get() = prefs.getInt(POST_FAVORITE, 0)
        set(value) = prefs.edit().putInt(POST_FAVORITE, value).apply()
}