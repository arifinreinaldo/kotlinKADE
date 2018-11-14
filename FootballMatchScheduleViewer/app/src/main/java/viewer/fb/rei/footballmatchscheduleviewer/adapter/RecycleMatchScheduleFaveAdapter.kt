package viewer.fb.rei.footballmatchscheduleviewer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.item_list.view.*
import viewer.fb.rei.footballmatchscheduleviewer.R
import viewer.fb.rei.footballmatchscheduleviewer.model.LeagueEventModal
import viewer.fb.rei.footballmatchscheduleviewer.util.formatDayddMMMyyyy

class RecycleMatchScheduleFaveAdapter(private val context: Context?, private val items: List<LeagueEventModal>, private val listener : (LeagueEventModal) -> Unit)
    : RecyclerView.Adapter<RecycleMatchScheduleFaveAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val image = view.findViewById<ImageView>(R.id.image)

        fun bindItem(items: LeagueEventModal, listener: (LeagueEventModal) -> Unit) {
            itemView.homeTeam.text = items.strHomeTeam
            itemView.homeScore.text = items.idHomeScore
            itemView.awayTeam.text = items.strAwayTeam
            itemView.awayScore.text = items.idAwayScore
            itemView.eventDate.text = items.dateEvent?.formatDayddMMMyyyy()
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}