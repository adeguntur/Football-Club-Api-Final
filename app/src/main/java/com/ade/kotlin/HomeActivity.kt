package com.ade.kotlin


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ade.kotlin.R.id.*
import com.ade.kotlin.fragment.FavoriteTeamsFragment
import com.ade.kotlin.fragment.FavoriteMatchFragment
import com.ade.kotlin.fragment.MatchFragment
import com.ade.kotlin.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                Match -> {
                    loadPastMatchFragment(savedInstanceState)
                }
                team -> {
                    loadNextMatchFragment(savedInstanceState)
                }
                favoriteMatch -> {
                    loadFavoritesFragment(savedInstanceState)
                }
                favorites_team -> {
                    loadFavoritesTeamFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = Match
    }

    private fun loadPastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment()
                            , MatchFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment()
                            , TeamsFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMatchFragment()
                            , FavoriteMatchFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeamsFragment()
                            , FavoriteTeamsFragment::class.simpleName)
                    .commit()
        }
    }
}
