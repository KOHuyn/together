package com.kohuyn.together.ui.count.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.BaseViewHolder
import com.kohuyn.together.R
import com.kohuyn.together.data.model.Event
import com.utils.ext.inflateExt
import kotlinx.android.synthetic.main.item_event.view.*

class CountAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var listEvent= mutableListOf<Event>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(parent.inflateExt(R.layout.item_event))

    override fun getItemCount(): Int = listEvent.size
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
       val event = listEvent[position]
        with(holder.itemView){
            txt_date_event.text = event.dateEvent
            txt_name_event.text = event.nameEvent
            txt_count_date.text = event.countDateEvent
        }
    }
}