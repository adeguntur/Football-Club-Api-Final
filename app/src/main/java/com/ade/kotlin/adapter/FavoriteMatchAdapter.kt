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
import com.ade.kotlin.db.FavoriteMatch
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class FavoriteMatchAdapter(private val favoriteMatches: List<FavoriteMatch>
                           , private val listener: (FavoriteMatch) -> Unit)
    : RecyclerView.Adapter<FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(FavoriteMatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favoriteMatches.size

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(favoriteMatches[position], listener)
    }
}

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val homeName: TextView = view.find(txt_home_name)
    private val awayName: TextView = view.find(txt_away_name)
    private val score: TextView = view.find(txt_score_match)
    private val matchDate: TextView = view.find(txt_match_date)
    private val cv: CardView = view.find(crv_match)

    fun bindItem(favoriteMatchMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
        homeName.text = favoriteMatchMatch.strHomeTeam
        awayName.text = favoriteMatchMatch.strAwayTeam
        if (favoriteMatchMatch.intHomeScore != null) {
            score.text = favoriteMatchMatch.intHomeScore + " VS " + favoriteMatchMatch.intAwayScore
        } else {
            score.text = "? VS ?"
        }
        matchDate.text = favoriteMatchMatch.dateEvent
        cv.setOnClickListener { _: View ->
            listener(favoriteMatchMatch)
        }
    }
}

class FavoriteMatchUI : AnkoComponent<ViewGroup> {
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
