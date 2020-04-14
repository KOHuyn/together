package com.kohuyn.together.ui.memories.adapter

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.base.BaseViewHolder
import com.kohuyn.together.R
import com.kohuyn.together.data.model.Memory
import com.kohuyn.together.ui.utils.showCenterInside
import com.kohuyn.zoomy.Zoomy
import com.utils.ext.inflateExt
import com.utils.ext.setVisibility
import kotlinx.android.synthetic.main.item_memories.view.*

class MemoriesAdapter(private val activity: Activity) : RecyclerView.Adapter<BaseViewHolder>() {

    var listMemories = mutableListOf<Memory>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickPopup:OnClickPopup?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(parent.inflateExt(R.layout.item_memories))

    override fun getItemCount(): Int = listMemories.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val memory = listMemories[position]
        with(holder.itemView) {
            img_memories.showCenterInside(memory.image)
            txt_content_memories.text = memory.content
            txt_date_memories.text = memory.date
            btn_three_dots.setOnClickListener {
                onClickPopup?.onClickPopup(position,it)
            }
            if(memory.content.isEmpty()){
                txt_content_memories.setVisibility(false)
            }
            if(memory.image.isEmpty()){
                img_memories.setVisibility(false)
            }
        }
        Zoomy.Builder(activity)
            .target(holder.itemView.img_memories)
            .interpolator(OvershootInterpolator())
            .tapListener {
                Log.e("tap", "onTap")
            }
            .longPressListener { }
            .doubleTapListener { }.also {
                it.register()
            }
    }
    interface OnClickPopup{
        fun onClickPopup(position: Int,view:View)
    }
}