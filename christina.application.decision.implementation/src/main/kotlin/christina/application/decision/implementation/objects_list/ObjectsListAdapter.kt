package christina.application.decision.implementation.objects_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import christina.application.decision.implementation.objects_list.ObjectsListAdapter.ObjectViewHolder
import christina.application.decision.implementation.R
import christina.application.decision.presentation.objects_viewer.model.CreatedObjectModel

class ObjectsListAdapter
    : RecyclerView.Adapter<ObjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        return ObjectViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_objects_list_item, parent))
    }

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        val decision = items!![position]
        holder.decisionNameView.text = decision.name
    }

    override fun getItemCount(): Int = items?.size ?: 0

    var items: List<CreatedObjectModel>? = null

    class ObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val decisionNameView: TextView = itemView.findViewById(R.id.decision_name) as TextView
    }
}