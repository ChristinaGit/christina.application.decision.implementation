package moe.christina.decision.objects_list.domain.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import moe.christina.common.android.view.recycler.adapter.RecyclerViewAdapter
import moe.christina.common.android.view.recycler.adapter.RecyclerViewHolder
import moe.christina.decision.R
import moe.christina.decision.objects_list.domain.adapter.ObjectsListAdapter.ObjectViewHolder
import moe.christina.decision.objects_viewer.domain.model.CreatedObject

class ObjectsListAdapter
    : RecyclerViewAdapter<ObjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder =
        ObjectViewHolder(inflateView(R.layout.adapter_objects_list_item, parent))

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        val decision = items!![position]
        holder.decisionNameView.text = decision.name
    }

    override fun getItemCount(): Int = items?.size ?: 0

    var items: List<CreatedObject>? = null

    class ObjectViewHolder(itemView: View) : RecyclerViewHolder(itemView) {
        val decisionNameView: TextView = itemView.findViewById(R.id.decision_name) as TextView
    }
}