package com.ade.kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ade.kotlin.R.id.*
import com.ade.kotlin.model.PlayerTeam
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerTeamAdapter(private val teams: List<PlayerTeam>, private val listener: (PlayerTeam) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PemainUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size

}

class PemainUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(8)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = team_badge_player
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent) {
                        leftMargin = dip(8)
                    }
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = team_name_player
                        textSize = 18f
                    }.lparams {
                    }
                    textView {
                        id = team_position_player
                        textSize = 12f
                    }.lparams {
                        leftMargin = dip(4)
                        topMargin = dip(4)
                    }
                }
            }
        }
    }

}


class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamBadge: ImageView = view.find(team_badge_player)
    private val teamName: TextView = view.find(team_name_player)
    private val teamPosition: TextView = view.find(team_position_player)

    fun bindItem(teams: PlayerTeam, listener: (PlayerTeam) -> Unit) {
        Picasso.get().load(teams.strCutout).into(teamBadge)
        teamName.text = teams.strPlayer
        teamPosition.text = teams.strPosition
        Log.d("myPTA2", teams.strPlayer)
        itemView.onClick { listener(teams) }
    }
}