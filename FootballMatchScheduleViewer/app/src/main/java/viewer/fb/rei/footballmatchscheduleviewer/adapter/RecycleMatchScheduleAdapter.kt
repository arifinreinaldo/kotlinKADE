package viewer.fb.rei.footballmatchscheduleviewer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.item_list.view.*
import viewer.fb.rei.footballmatchscheduleviewer.R
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueEvent
import viewer.fb.rei.footballmatchscheduleviewer.util.formatDayddMMMyyyy

class RecycleMatchScheduleAdapter(private val context: Context?, private val items: List<LeagueEvent>,private val listener : (LeagueEvent) -> Unit)
    : RecyclerView.Adapter<RecycleMatchScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val image = view.findViewById<ImageView>(R.id.image)

        fun bindItem(items: LeagueEvent, listener: (LeagueEvent) -> Unit) {
            itemView.homeTeam.text = items.strHomeTeam
            itemView.homeScore.text = items.intHomeScore
            itemView.awayTeam.text = items.strAwayTeam
            itemView.awayScore.text = items.intAwayScore
            itemView.eventDate.text = items.dateEvent.formatDayddMMMyyyy()
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}