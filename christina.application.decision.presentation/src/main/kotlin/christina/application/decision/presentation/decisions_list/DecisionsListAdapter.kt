package christina.application.decision.presentation.decisions_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import christina.application.decision.presentation.R
import org.jetbrains.anko.find

class DecisionsListAdapter(
    val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<DecisionsListAdapter.ViewHolder>() {
    var items: List<String?>? = null
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(R.layout.decisions_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.apply {
            decisionName.text = items?.getOrNull(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val decisionName: TextView = view.find(R.id.decision_name)
    }
}