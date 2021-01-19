package com.sagamore.testapplication.feature_main_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagamore.testapplication.R
import com.sagamore.testapplication.feature_details.ItemDetailActivity
import com.sagamore.testapplication.feature_details.ItemDetailFragment
import com.sagamore.testapplication.service.data.EmployeeModel

class SimpleItemRecyclerViewAdapter(
    private val parentActivity: ItemListActivity,
    private val twoPane: Boolean
) :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private var employees: List<EmployeeModel> = arrayListOf()
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as EmployeeModel
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ItemDetailFragment.ARG_ITEM_ID, item)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    putExtra(ItemDetailFragment.ARG_ITEM_ID, item)
                }
                v.context.startActivity(intent)
            }
        }
    }

    internal fun setData(employeeList: List<EmployeeModel>) {
        employees = employeeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = employees[position]
        holder.idView.text = item.f_name
        holder.contentView.text = item.l_name

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = employees.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.id_text)
        val contentView: TextView = view.findViewById(R.id.content)
    }
}
