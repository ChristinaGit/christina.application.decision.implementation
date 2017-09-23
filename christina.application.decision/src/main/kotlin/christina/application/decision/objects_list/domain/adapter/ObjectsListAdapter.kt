package christina.application.decision.objects_list.domain.adapter

import android.view.View
import android.view.ViewGroup
import christina.library.common.android.view.recycler.view_holder.RecyclerViewHolderExtensions
import moe.christina.decision.R
import christina.application.decision.objects_list.domain.adapter.ObjectsListAdapter.ObjectViewHolder
import christina.application.decision.objects_viewer.domain.model.CreatedObject

class ObjectsListAdapter
    : RecyclerViewAdapter<ObjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder =
        ObjectViewHolder(inflateView(R.layout.adapter_objects_list_item, parent))

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        val decision = items!![position]
//        holder.decisionNameView.text = decision.name
    }

    override fun getItemCount(): Int = items?.size ?: 0

    var items: List<CreatedObject>? = null

    class ObjectViewHolder(itemView: View) : RecyclerViewHolderExtensions(itemView) {
//        val decisionNameView: TextView = itemView.findViewById(R.id.decision_name) as TextView
    }
}