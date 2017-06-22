package moe.christina.decision.screen.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import moe.christina.common.android.view.recycler.adapter.RecyclerViewHolder
import moe.christina.common.android.view.recycler.adapter.RecyclerViewListAdapter
import moe.christina.decision.R
import moe.christina.decision.model.Decision

class DecisionsListAdapter(override var items: List<Decision>? = null) : RecyclerViewListAdapter<Decision, DecisionViewHolder>() {
    override fun onCreateInnerItemViewHolder(parent: ViewGroup, viewType: Int): DecisionViewHolder {
        return DecisionViewHolder(inflateView(R.layout.adapter_decisions_list_item, parent))
    }

    override fun onBindInnerItemViewHolder(holder: DecisionViewHolder, position: Int) {
        val decision = getItem(position)
        holder.decisionNameView.text = decision.name
    }
}

class DecisionViewHolder(itemView: View) : RecyclerViewHolder(itemView) {
    val decisionNameView: TextView = itemView.findViewById(R.id.decision_name) as TextView

}