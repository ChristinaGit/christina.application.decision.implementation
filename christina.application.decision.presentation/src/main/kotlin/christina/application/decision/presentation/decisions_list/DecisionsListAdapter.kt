package christina.application.decision.presentation.decisions_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import christina.application.decision.presentation.R
import christina.application.decision.presentation.decisions_list.DecisionsListAdapter.ViewHolder
import christina.common.rx.event.ValueEvent
import christina.common.rx.event.invoke
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

class DecisionsListAdapter(
    val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<ViewHolder>(),
    AnkoLogger {
    var items: List<String?> = emptyList()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(R.layout.decisions_list_item, parent, false))
            .apply {
                itemView.setOnClickListener(itemClickListener)
            }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.apply {
            itemView.tag = holder

            val item = items.getOrNull(position)

            decisionNameView.text = item
        }
    }

    val onItemClick: Observable<ValueEvent<Int>>
        get() = onItemClickEvent.hide()

    private val itemClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag
        if (viewHolder is ViewHolder) {
            onItemClickEvent(viewHolder.adapterPosition)
        }
    }

    private val onItemClickEvent: Subject<ValueEvent<Int>> = PublishSubject.create()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val decisionNameView: TextView = view.find(R.id.decision_name)
    }
}