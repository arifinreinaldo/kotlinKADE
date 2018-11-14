package viewer.fb.rei.footballmatchscheduleviewer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.item_player_list.view.*
import viewer.fb.rei.footballmatchscheduleviewer.R
import viewer.fb.rei.footballmatchscheduleviewer.structure.Player
import viewer.fb.rei.footballmatchscheduleviewer.util.loadGlide

class RecyclePlayerMemberAdapter(private val context: Context?, private val items: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<RecyclePlayerMemberAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_player_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image = view.findViewById<ImageView>(R.id.image)

        fun bindItem(items: Player, listener: (Player) -> Unit) {
            itemView.playerName.text = items.strPlayer
//            dbg("MEME "+items.strCutout)
            items.strCutout?.let { itemView.playerImage.loadGlide(items.strCutout) }
//            itemView.playerImage.loadGlide(items.strCutout)
            itemView.playerPosition.text = items.strPosition
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}