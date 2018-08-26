package com.ade.kotlin

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.ade.kotlin.R.drawable.ic_add_to_favorites
import com.ade.kotlin.R.drawable.ic_added_to_favorites
import com.ade.kotlin.R.id.*
import com.ade.kotlin.R.menu.detail_menu
import com.ade.kotlin.model.MatchEvent
import com.ade.kotlin.model.Team
import com.ade.kotlin.api.API
import com.ade.kotlin.db.FavoriteMatch
import com.ade.kotlin.db.database
import com.ade.kotlin.view.MatchInterface
import com.ade.kotlin.presenter.DetailPresenter
import com.ade.kotlin.presenter.MatchDetailPresenter
import com.ade.kotlin.key.KEY
import kotlinx.android.synthetic.main.activity_match_detail.*
import com.ade.kotlin.view.DetailInterface
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast


class DetailMatchActivity : AppCompatActivity(), DetailInterface, MatchInterface {

    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var idEvent: String

    private lateinit var txtMatchDate: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var detailPresenter: DetailPresenter
    private lateinit var detailMatchPresenter: MatchDetailPresenter

    private lateinit var teamHome: Team
    private lateinit var teamAway: Team
    private lateinit var matchEvent: MatchEvent

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        supportActionBar?.title = "Detail Pertandingan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txtMatchDate = findViewById(R.id.mtch_date)
        progressBar = findViewById(R.id.progress_bar_detail)

        val myIntent = intent

        idHomeTeam = myIntent.getStringExtra(KEY.ID_HOME_KEY)
        idAwayTeam = myIntent.getStringExtra(KEY.ID_AWAY_KEY)
        idEvent = myIntent.getStringExtra(KEY.ID_EVENT_KEY)

        favoriteState()
        val request = API()
        val gson = Gson()

        detailPresenter = DetailPresenter(this, request, gson)
        detailMatchPresenter = MatchDetailPresenter(this, request, gson)

        detailPresenter.geDetailTeamList(idHomeTeam, idAwayTeam)
        detailMatchPresenter.getDetailMatch(idEvent)

    }

    private fun View.visible() {
        visibility = View.VISIBLE
    }

    private fun View.invisible() {
        visibility = View.GONE
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamsList(data: List<Team>?, data2: List<Team>?) {
        teamHome = Team(data?.get(0)?.strTeamBadge)
        teamAway = Team(data2?.get(0)?.strTeamBadge)
        Picasso.get().load(data?.get(0)?.strTeamBadge).into(img_home)
        Picasso.get().load(data2?.get(0)?.strTeamBadge).into(img_away)
    }

    override fun showMatchEventList(match: List<MatchEvent>?) {
        matchEvent = MatchEvent(match?.get(0)?.idEvent,
                match?.get(0)?.strHomeTeam,
                match?.get(0)?.strAwayTeam,
                match?.get(0)?.intHomeScore,
                match?.get(0)?.intAwayScore,
                match?.get(0)?.dateEvent,
                match?.get(0)?.strHomeLineupGoalkeeper,
                match?.get(0)?.strAwayLineupGoalkeeper,
                match?.get(0)?.strHomeGoalDetails,
                match?.get(0)?.strAwayGoalDetails,
                match?.get(0)?.intHomeShots,
                match?.get(0)?.intAwayShots,
                match?.get(0)?.strHomeLineupDefense,
                match?.get(0)?.awayDefense,
                match?.get(0)?.strAwayLineupDefense,
                match?.get(0)?.strAwayLineupMidfield,
                match?.get(0)?.strHomeLineupForward,
                match?.get(0)?.strAwayLineupForward,
                match?.get(0)?.strHomeLineupSubstitutes,
                match?.get(0)?.strAwayLineupSubstitutes,
                match?.get(0)?.strHomeFormation,
                match?.get(0)?.strAwayFormation,
                match?.get(0)?.strTeamBadge,
                match?.get(0)?.idHomeTeam,
                match?.get(0)?.idAwayTeam)

        name_home.text = match?.get(0)?.strHomeTeam
        home_score_mtch.text = match?.get(0)?.intHomeScore
        goals_home.text = match?.get(0)?.strHomeGoalDetails
        goalkeeper_home.text = match?.get(0)?.strHomeLineupGoalkeeper
        shots_home.text = match?.get(0)?.intHomeShots
        defense_home.text = match?.get(0)?.strHomeLineupDefense
        forward_home.text = match?.get(0)?.strHomeLineupForward
        substitutes_home.text = match?.get(0)?.strHomeLineupSubstitutes
        midfield_home.text = match?.get(0)?.strAwayLineupDefense

        name_away.text = match?.get(0)?.strAwayTeam
        away_score_mtch.text = match?.get(0)?.intAwayScore
        goals_away.text = match?.get(0)?.strAwayGoalDetails
        goalkeeper_away.text = match?.get(0)?.strAwayLineupGoalkeeper
        shots_away.text = match?.get(0)?.intAwayShots
        defense_away.text = match?.get(0)?.awayDefense
        forward_away.text = match?.get(0)?.strAwayLineupForward
        substitutes_away.text = match?.get(0)?.strAwayLineupSubstitutes
        midfield_away.text = match?.get(0)?.strAwayLineupMidfield

        mtch_date.text = match?.get(0)?.dateEvent
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                try {
                    if (isFavorite) removeFromFavorite() else addToFavorite()

                    isFavorite = !isFavorite
                    setFavorite()
                } catch (e: Exception) {
                    toast("Coba,Lagi")
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE,
                        FavoriteMatch.ID_EVENT to matchEvent.idEvent,
                        FavoriteMatch.HOME_TEAM_STR to matchEvent.strHomeTeam,
                        FavoriteMatch.AWAY_TEAM_STR to matchEvent.strAwayTeam,
                        FavoriteMatch.INT_HOME_SCORE to matchEvent.intHomeScore,
                        FavoriteMatch.INT_AWAY_SCORE to matchEvent.intAwayScore,
                        FavoriteMatch.DATE_EVENT to matchEvent.dateEvent,
                        FavoriteMatch.HOME_LINEUP_GOALKEEPER_STR to matchEvent.strHomeLineupGoalkeeper,
                        FavoriteMatch.AWAY_LINEUP_GOALKEEPER_STR to matchEvent.strAwayLineupGoalkeeper,
                        FavoriteMatch.HOME_GOAL_DETAILS_STR to matchEvent.strHomeGoalDetails,
                        FavoriteMatch.AWAY_GOAL_DETAILS_STR to matchEvent.strAwayGoalDetails,
                        FavoriteMatch.INT_HOME_SHOTS to matchEvent.intHomeShots,
                        FavoriteMatch.INT_AWAY_SHOTS to matchEvent.intAwayShots,
                        FavoriteMatch.HOME_LINEUP_DEFENSE_STR to matchEvent.strHomeLineupDefense,
                        FavoriteMatch.AWAY_DEFENSE to matchEvent.awayDefense,
                        FavoriteMatch.AWAY_LINEUP_DEFENSE_STR to matchEvent.strAwayLineupDefense,
                        FavoriteMatch.AWAY_LINEUP_MIDFIELD_STR to matchEvent.strAwayLineupMidfield,
                        FavoriteMatch.HOME_LINEUP_FORWARD_STR to matchEvent.strHomeLineupForward,
                        FavoriteMatch.AWAY_LINEUP_FORWARD_STR to matchEvent.strAwayLineupForward,
                        FavoriteMatch.HOME_LINEUP_SUBSTITUTES_STR to matchEvent.strHomeLineupSubstitutes,
                        FavoriteMatch.AWAY_LINEUP_SUBSTITUTES_STR to matchEvent.strAwayLineupSubstitutes,
                        FavoriteMatch.HOME_FORMATION_STR to matchEvent.strHomeFormation,
                        FavoriteMatch.AWAY_FORMATION_STR to matchEvent.strAwayFormation,
                        FavoriteMatch.TEAM_BADGE_STR to matchEvent.strTeamBadge,
                        FavoriteMatch.ID_HOME_TEAM to matchEvent.idHomeTeam,
                        FavoriteMatch.ID_AWAY_TEAM to matchEvent.idAwayTeam)
            }
            Snackbar.make(findViewById(R.id.ll_detail),
                    "Anda Menyukai ini", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(findViewById(R.id.ll_detail),
                    "gagal", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE,
                        "(ID_EVENT = {idEvent})",
                        "idEvent" to idEvent)
            }
            Snackbar.make(findViewById(R.id.ll_detail),
                    "Batal Sukai", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(findViewById(R.id.ll_detail),
                    "gagal", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                    ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                    ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
                    .whereArgs("(ID_EVENT = {idEvent})", "idEvent" to idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
