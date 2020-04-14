package com.kohuyn.together.ui.memories

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import com.base.BaseFragment
import com.google.gson.Gson
import com.kohuyn.together.BR
import com.kohuyn.together.R
import com.kohuyn.together.data.model.Memory
import com.kohuyn.together.databinding.FragmentMemoriesBinding
import com.kohuyn.together.ui.memories.adapter.MemoriesAdapter
import com.kohuyn.together.ui.memories.addstory.AddStoryFragment
import com.kohuyn.together.ui.utils.Key
import com.kohuyn.together.ui.utils.event.EventNextAddStory
import com.kohuyn.together.ui.utils.event.EventSaveMemory
import com.kohuyn.together.ui.utils.postNormal
import com.kohuyn.together.ui.utils.register
import com.kohuyn.together.ui.utils.unregister
import com.utils.ext.setVisibility
import kotlinx.android.synthetic.main.item_memories.view.*
import org.greenrobot.eventbus.Subscribe
import org.koin.android.ext.android.inject

import org.koin.android.viewmodel.ext.android.viewModel

class MemoriesFragment:BaseFragment<FragmentMemoriesBinding,MemoriesViewModel>(),MemoriesNavigator {
    private val memoriesViewModel by viewModel<MemoriesViewModel>()

    private val memoriesAdapter by lazy { activity?.let { MemoriesAdapter(it) } }

    private val gson by inject<Gson>()

    private var listMemories = mutableListOf<Memory>()

    override fun getBindingVariable(): Int = BR.memoriesViewModel

    override fun getLayoutId(): Int = R.layout.fragment_memories

    override fun getViewModel(): MemoriesViewModel = memoriesViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
        memoriesViewModel.setNavigator(this)
        memoriesViewModel.getAllMemories()
        setupRcv()
    }

    override fun addStory() {
        postNormal(EventNextAddStory(AddStoryFragment::class.java,true))
        Thread.sleep(500)
    }

    private fun setupRcv(){
        setUpRcv(binding!!.rcvMemories,memoriesAdapter!!)
        memoriesAdapter!!.onClickPopup = object :MemoriesAdapter.OnClickPopup{
            override fun onClickPopup(position: Int, view: View) {
                val popup = PopupMenu(activity,view.btn_three_dots,Gravity.RIGHT)
                popup.menuInflater.inflate(R.menu.popup,popup.menu)
                popup.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.edit ->{
                            val bundle = Bundle().apply {
                                putString(Key.KEY_PUT_MEMORY,gson.toJson(listMemories[position]))
                            }
                            postNormal(EventNextAddStory(AddStoryFragment::class.java,bundle,true))
                        }

                        R.id.delete ->{
                            memoriesViewModel.deleteMemory(listMemories[position])
                            listMemories.removeAt(position)
                            memoriesAdapter!!.notifyDataSetChanged()
                            setNewStory(listMemories.isEmpty())
                        }
                    }
                    true
                }
                popup.show()
            }
        }
    }

    @Subscribe
    fun returnSaveMemory(eventSaveMemory: EventSaveMemory){
        if(eventSaveMemory.isSave){
            memoriesViewModel.getAllMemories()
            Handler().postDelayed({
                setupRcv()
            },1000)
        }
    }

    override fun onStart() {
        super.onStart()
        register(this)
    }

    override fun onStop() {
        super.onStop()
        unregister(this)
    }

    private fun setNewStory(isNew:Boolean){
        if(isNew){
            binding!!.lnNewStory.setVisibility(true)
            binding!!.rcvMemories.setVisibility(false)
        }else{
            binding!!.lnNewStory.setVisibility(false)
            binding!!.rcvMemories.setVisibility(true)
        }
    }

    override fun getAllMemories(memories: List<Memory>) {
       setNewStory(memories.isEmpty())
       this.listMemories = memories as MutableList<Memory>
        memoriesAdapter!!.listMemories = listMemories
    }
}