package com.ade.kotlin.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.R.layout.support_simple_spinner_dropdown_item
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*

import com.ade.kotlin.R
import com.ade.kotlin.R.array.*
import com.ade.kotlin.api.API
import com.ade.kotlin.model.MatchEvent
import com.ade.kotlin.DetailMatchActivity
import com.ade.kotlin.key.KEY
import com.ade.kotlin.util.invisible
import com.ade.kotlin.util.visible
import com.ade.kotlin.R.id.edt_search
import com.ade.kotlin.R.string.*
import com.ade.kotlin.adapter.MatchAdapter
import com.ade.kotlin.view.MatchInterface
import com.ade.kotlin.presenter.NextMatchPresenter
import com.ade.kotlin.presenter.PastMatchPresenter
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFragment : Fragment(), AnkoComponent<Context>, MatchInterface {

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var fieldSearch: EditText
    private lateinit var searchButton: ImageButton

    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner

    private var matchEvent: MutableList<MatchEvent> = mutableListOf()
    private lateinit var pastMatchPresenter: PastMatchPresenter
    private lateinit var nextMatchPresenter: NextMatchPresenter
    private lateinit var adapter: MatchAdapter

    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fieldSearch = view!!.findViewById(edt_search)

        adapter = MatchAdapter(matchEvent) {
            startActivity<DetailMatchActivity>(
                    KEY.ID_HOME_KEY to it.idHomeTeam,
                    KEY.ID_AWAY_KEY to it.idAwayTeam,
                    KEY.ID_EVENT_KEY to it.idEvent)
        }
        listTeam.adapter = adapter

        val request = API()
        val gson = Gson()
        pastMatchPresenter = PastMatchPresenter(this, request, gson)
        nextMatchPresenter = NextMatchPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(mtch_list)
        val spinnerAdapter = ArrayAdapter(ctx, support_simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        val spinnerItemsLeague = resources.getStringArray(league_list)
        val spinnerAdapterLeague = ArrayAdapter(ctx, support_simple_spinner_dropdown_item, spinnerItemsLeague)
        spinner2.adapter = spinnerAdapterLeague

        pastMatchPresenter.getMatchList(getString(english_pemier_league_mtch),
                getString(R.string.no_parameter))

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (spinner.selectedItem == getString(past_match) &&
                        spinner2.selectedItem == getString(spanish_la_liga)) {

                    pastMatchPresenter.getMatchList(getString(spanish_la_liga_mtch),
                            getString(R.string.no_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(spanish_la_liga_mtch),
                                getString(R.string.no_parameter))
                    }

                }
                if (spinner.selectedItem == getString(past_match) &&
                        spinner2.selectedItem == getString(english_pemier_league)) {

                    pastMatchPresenter.getMatchList(getString(english_pemier_league_mtch),
                            getString(R.string.no_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(english_pemier_league_mtch),
                                getString(R.string.no_parameter))
                    }

                }

                if (spinner.selectedItem == getString(past_match) &&
                        spinner2.selectedItem == getString(english_league_championship)) {

                    pastMatchPresenter.getMatchList(getString(english_league_championship_mtch),
                            getString(R.string.no_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(english_league_championship_mtch),
                                getString(R.string.no_parameter))
                    }
                }
                if (spinner.selectedItem == getString(past_match) &&
                        spinner2.selectedItem == getString(german_bundes_liga)) {

                    pastMatchPresenter.getMatchList(getString(german_bundes_liga_mtch),
                            getString(R.string.no_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(german_bundes_liga),
                                getString(R.string.no_parameter))
                    }

                }
                if (spinner.selectedItem == getString(past_match) &&
                        spinner2.selectedItem == getString(italian_serie_A)) {

                    pastMatchPresenter.getMatchList(getString(italian_serie_A_mtch),
                            getString(R.string.no_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(italian_serie_A_mtch),
                                getString(R.string.no_parameter))
                    }

                }
                if (spinner.selectedItem == getString(past_match) &&
                        spinner2.selectedItem == getString(french_ligue_1)) {

                    pastMatchPresenter.getMatchList(getString(french_ligue_1_mtch),
                            getString(R.string.no_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(french_ligue_1_mtch),
                                getString(R.string.no_parameter))
                    }

                }
                if (spinner.selectedItem == getString(nextMatch) &&
                        spinner2.selectedItem == getString(spanish_la_liga)) {

                    nextMatchPresenter.getMatchList(getString(spanish_la_liga_mtch))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(spanish_la_liga_mtch))
                    }

                }
                if (spinner.selectedItem == getString(nextMatch) &&
                        spinner2.selectedItem == getString(english_pemier_league)) {

                    nextMatchPresenter.getMatchList(getString(english_pemier_league_mtch))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(english_pemier_league_mtch))
                    }

                }
                if (spinner.selectedItem == getString(nextMatch) &&
                        spinner2.selectedItem == getString(english_league_championship)) {

                    nextMatchPresenter.getMatchList(getString(english_league_championship_mtch))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(english_league_championship_mtch))
                    }

                }
                if (spinner.selectedItem == getString(nextMatch) &&
                        spinner2.selectedItem == getString(german_bundes_liga)) {

                    nextMatchPresenter.getMatchList(getString(german_bundes_liga_mtch))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(german_bundes_liga_mtch))
                    }

                }
                if (spinner.selectedItem == getString(nextMatch) &&
                        spinner2.selectedItem == getString(italian_serie_A)) {

                    nextMatchPresenter.getMatchList(getString(italian_serie_A_mtch))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(italian_serie_A_mtch))
                    }

                }
                if (spinner.selectedItem == getString(nextMatch) &&
                        spinner2.selectedItem == getString(french_ligue_1)) {

                    nextMatchPresenter.getMatchList(getString(french_ligue_1_mtch))
                }

                swipeRefresh.onRefresh {
                    nextMatchPresenter.getMatchList(getString(french_ligue_1_mtch))
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinner.onItemSelectedListener = spinner2.onItemSelectedListener
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

             appBarLayout {
                    lparams(matchParent, wrapContent)
                 topPadding = dip(16)
                 leftPadding = dip(16)
                 rightPadding = dip(16)
                 bottomPadding = dip(8)

                 linearLayout {
                     lparams(width = matchParent, height = wrapContent)
                     orientation = LinearLayout.HORIZONTAL

                     fieldSearch = editText {
                         singleLine = true
                         id = edt_search
                         hint = "Cari Pertandingan"
                         setTextColor(getResources().getColor(R.color.putih))
                         setHintTextColor(getResources().getColor(R.color.putih))
                     }.lparams(width = dip(0), height = wrapContent, weight = 5f)

                     searchButton = imageButton {
                         imageResource = R.drawable.ic_search_black_24dp
                         backgroundColor = 80000000
                         onClick {
                             pastMatchPresenter.getMatchList(getString(R.string.no_parameter),
                                     fieldSearch.textValue())
                         }
                     }.lparams(width = dip(0), height = wrapContent, weight = 1f)

                 }

                }


            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.HORIZONTAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)
                bottomPadding = dip(8)
                spinner = spinner {}
                        .lparams(width = dip(0), height = wrapContent, weight = 1f)

                spinner2 = spinner {}
                        .lparams(width = dip(0), height = wrapContent, weight = 1f)
            }

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = R.id.rv_list_team
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerInParent()
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

    override fun showMatchEventList(data: List<MatchEvent>?) {
        swipeRefresh.isRefreshing = false
        matchEvent.clear()
        data?.let {
            matchEvent.addAll(data)
            adapter.notifyDataSetChanged()
        } ?: toast(getString(R.string.empty_data))
    }

    fun EditText.textValue() = text.toString()
}

