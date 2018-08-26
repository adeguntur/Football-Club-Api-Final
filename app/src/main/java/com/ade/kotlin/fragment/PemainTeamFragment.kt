package com.ade.kotlin.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ade.kotlin.R.color.colorAccent
import com.ade.kotlin.api.API
import com.ade.kotlin.TeamPagerAdapter
import com.ade.kotlin.PemainDetailActivity
import com.ade.kotlin.adapter.PlayerTeamAdapter
import com.ade.kotlin.presenter.PemainTeamPresenter
import com.ade.kotlin.model.PlayerTeam
import com.ade.kotlin.util.invisible
import com.ade.kotlin.util.visible
import com.ade.kotlin.view.PemainTeamInterface
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PemainTeamFragment : Fragment(), AnkoComponent<Context>, PemainTeamInterface {

    private var playerTeams: MutableList<PlayerTeam> = mutableListOf()
    private lateinit var presenter: PemainTeamPresenter
    private lateinit var adapter: PlayerTeamAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var idTeam: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = PlayerTeamAdapter(playerTeams) {
            ctx.startActivity<PemainDetailActivity>("id" to "${it.idPlayer}")
        }
        listEvent.adapter = adapter

        val bindData = arguments
        idTeam = bindData?.getString(TeamPagerAdapter.KEY_TEAM_2) ?: "idTeam"

        val request = API()
        val gson = Gson()

        presenter = PemainTeamPresenter(this, request, gson)
        presenter.getPlayerList(idTeam)


        swipeRefresh.onRefresh {
            presenter.getPlayerList(idTeam)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerList(data: List<PlayerTeam>) {
        swipeRefresh.isRefreshing = false
        playerTeams.clear()
        playerTeams.addAll(data)
        adapter.notifyDataSetChanged()

    }

}
