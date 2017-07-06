package moe.christina.decision.screen.adapter

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import moe.christina.common.android.view.recycler.adapter.RecyclerViewHolder
import moe.christina.common.android.view.recycler.adapter.HeaderListRecyclerViewAdapter
import moe.christina.decision.R
import moe.christina.decision.model.data.Decision
import moe.christina.decision.screen.adapter.DecisionsListAdapter.DecisionViewHolder

class DecisionsListAdapter
    : HeaderListRecyclerViewAdapter<Decision, ViewHolder, DecisionViewHolder, ViewHolder>() {
    override fun onCreateInnerItemViewHolder(parent: ViewGroup, viewType: Int): DecisionViewHolder {
        return DecisionViewHolder(inflateView(R.layout.adapter_decisions_list_item, parent))
    }

    override fun onBindInnerItemViewHolder(holder: DecisionViewHolder, position: Int) {
        val decision = getItem(position)
        holder.decisionNameView.text = decision.name
    }

    class DecisionViewHolder(itemView: View) : RecyclerViewHolder(itemView) {
        val decisionNameView: TextView = itemView.findViewById(R.id.decision_name) as TextView
    }
}