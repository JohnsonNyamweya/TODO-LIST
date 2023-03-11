package com.nyamweyajohnson.todolistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
//class where the dagger components should be generated (application context)
class TodoApp: Application()