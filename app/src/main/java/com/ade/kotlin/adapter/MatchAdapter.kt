package com.ade.kotlin.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ade.kotlin.R
import com.ade.kotlin.R.id.*
import com.ade.kotlin.model.MatchEvent
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MatchAdapter(private val matchEvents: List<MatchEvent>, private val listener: (MatchEvent) -> Unit)
    : RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = matchEvents.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matchEvents[position], listener)
    }
}

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val homeName: TextView = view.find(txt_home_name)
    private val awayName: TextView = view.find(txt_away_name)
    private val score: TextView = view.find(txt_score_match)
    private val matchDate: TextView = view.find(txt_match_date)
    private val cv: CardView = view.find(crv_match)

    fun bindItem(events: MatchEvent, listener: (MatchEvent) -> Unit) {
        homeName.text = events.strHomeTeam
        awayName.text = events.strAwayTeam
        if (events.intHomeScore != null) {
            score.text = events.intHomeScore + " VS " + events.intAwayScore
        } else {
            score.text = "? VS ?"
        }
        matchDate.text = events.dateEvent
        cv.setOnClickListener { _: View ->
            listener(events)
        }
    }
}

class MatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                id = R.id.crv_match
                lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(16)
                    rightMargin = dip(16)
                    leftMargin = dip(16)
                }

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(16)

                    textView {
                        id = R.id.txt_match_date
                        gravity = Gravity.CENTER
                    }.lparams {
                        width = matchParent
                    }

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.HORIZONTAL

                        textView {
                            id = R.id.txt_home_name
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = matchParent
                            weight = 1f
                        }

                        textView {
                            id = R.id.txt_score_match
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = matchParent
                            weight = 1f
                        }

                        textView {
                            id = R.id.txt_away_name
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = matchParent
                            weight = 1f
                        }
                    }
                }
            }
        }
    }
}
