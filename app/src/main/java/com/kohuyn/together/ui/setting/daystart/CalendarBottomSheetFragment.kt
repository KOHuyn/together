package com.kohuyn.together.ui.setting.daystart

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kohuyn.together.R
import com.kohuyn.together.TogetherApplication
import kotlinx.android.synthetic.main.bottom_sheet_date_picker.*
import kotlinx.android.synthetic.main.bottom_sheet_date_picker.view.*
import java.util.*

class CalendarBottomSheetFragment :BottomSheetDialogFragment() {

    var pickerDateCallback:PickerDateCallback?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomBottomSheetDialogTheme)
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_date_picker,null)
        dialog.setContentView(view)
        view.txt_done.setOnClickListener {
            val day = view.date_picker.dayOfMonth
            val month = view.date_picker.month
            val year = view.date_picker.year
            val calendar:Calendar = GregorianCalendar(year,month ,day)
            if(calendar.timeInMillis > Calendar.getInstance().timeInMillis){
                Toast.makeText(TogetherApplication.getInstance(),TogetherApplication.getInstance().getText(R.string.str_invalid),Toast.LENGTH_SHORT).show()
            }else{
                pickerDateCallback?.onClickDate(calendar.timeInMillis)
            }
        }
    }
    interface PickerDateCallback{
        fun onClickDate(calendar:Long)
    }
}