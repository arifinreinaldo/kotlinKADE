package viewer.fb.rei.footballmatchscheduleviewer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.item_team_list.view.*
import viewer.fb.rei.footballmatchscheduleviewer.R
import viewer.fb.rei.footballmatchscheduleviewer.model.TeamModal
import viewer.fb.rei.footballmatchscheduleviewer.util.loadGlide

class RecycleTeamModalFaveAdapter(private val context: Context?, private val items: List<TeamModal>, private val listener: (TeamModal) -> Unit)
    : RecyclerView.Adapter<RecycleTeamModalFaveAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image = view.findViewById<ImageView>(R.id.image)

        fun bindItem(items: TeamModal, listener: (TeamModal) -> Unit) {
            itemView.teamName.text = items.strTeam
            itemView.teamImage.loadGlide(items.strTeamBadge!!)
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}