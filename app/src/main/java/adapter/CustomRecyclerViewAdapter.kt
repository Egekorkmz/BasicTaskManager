package com.mid.sukruegekorkmaz_hw2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mid.sukruegekorkmaz_hw2.R
import db.StudyTask
import db.Task


class CustomRecyclerViewAdapter( private val context: Context, private val recyclerItemValues: ArrayList<Task>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface RecyclerAdapterInterface{
        fun displayItem(sc: Task)
    }
    lateinit var recyclerAdapterInterface:RecyclerAdapterInterface

    init {
        recyclerAdapterInterface = context as RecyclerAdapterInterface
    }
    companion object {
        const val TYPE_NORMAL = 100
        const val TYPE_STUDY = 200
    }
    //STEP1:
    override fun getItemViewType(position: Int): Int {
        val sc = recyclerItemValues[position]
        /*
        Return  int value that corresponds to the how many layouts will be used. Here 100 or 200 will be returned
        100 corresponds to premium items and 200 corresponds to normal items.
         */
        return sc.type
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View
        val inflator = LayoutInflater.from(viewGroup.context)
        //STEP4
        return if (viewType == TYPE_STUDY) {
            itemView = inflator.inflate(R.layout.study_task, viewGroup, false)
            StudyCustomRecyclerViewItemHolder(itemView) // that statement means  return PremiumCustomRecyclerViewItemHolder(itemView)
        } else {
            itemView = inflator.inflate(R.layout.normal_task, viewGroup, false)
            NormalCustomRecyclerViewItemHolder(itemView) // that statement means  return NormalCustomRecyclerViewItemHolder(itemView)
        }
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = recyclerItemValues[position]
        //STEP6
        if (getItemViewType(position) == TYPE_STUDY) {
            var temp = currentItem as? StudyTask
            val itemView = myRecyclerViewItemHolder as StudyCustomRecyclerViewItemHolder
            itemView.studyItemName.text = currentItem.taskName
            itemView.imgStudyItem.setImageResource(currentItem.imgId)
            itemView.studyItemSubject.text = temp?.subject

            /*itemView.itemPremiumConstraintLayout.setOnClickListener {
                recyclerAdapterInterface.displayItem(currentItem)
            }*/
        } else if (getItemViewType(position) == TYPE_NORMAL) {
            //STEP7
            val itemView = myRecyclerViewItemHolder as NormalCustomRecyclerViewItemHolder
            itemView.tvNormalItemName.text = currentItem.taskName
            itemView.imgNormal.setImageResource(currentItem.imgId)

            /*
            itemView.itemNormalLinearLayout.setOnClickListener {
                Toast.makeText(context, currentItem.toString(), Toast.LENGTH_LONG).show()
            }*/
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemValues.size
    }

    //STEP2: inner class corresponds to the layout file which will be used for the premium items
    internal inner class StudyCustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var studyItemName: TextView
        var studyItemSubject: TextView
        var imgStudyItem: ImageView
        var itemStudyLayout: LinearLayout

        init {
            studyItemName = itemView.findViewById<TextView>(R.id.tv_recycleName)
            studyItemSubject = itemView.findViewById<TextView>(R.id.tv_recycleSubject)
            imgStudyItem = itemView.findViewById<ImageView>(R.id.iv_recycleImg)
            itemStudyLayout = itemView.findViewById<LinearLayout>(R.id.study_linearLayout)
        }
    }

    //STEP2: inner class corresponds to the layout file which will be used for the normal items
    internal inner class NormalCustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvNormalItemName: TextView
        var imgNormal: ImageView
        var itemNormalLinearLayout: LinearLayout

        init {
            tvNormalItemName = itemView.findViewById<TextView>(R.id.tv_recycleName)
            imgNormal = itemView.findViewById<ImageView>(R.id.iv_recycleImg)
            itemNormalLinearLayout = itemView.findViewById<LinearLayout>(R.id.normal_linearLayout)
        }
    }
}

