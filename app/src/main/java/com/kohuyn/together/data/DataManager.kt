package com.kohuyn.together.data

import com.kohuyn.together.data.db.DbHelper
import com.kohuyn.together.data.prefs.PrefsHelper

interface DataManager:PrefsHelper ,DbHelper{
}