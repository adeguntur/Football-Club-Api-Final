package com.ade.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ade.kotlin.fragment.DescTeamFragment
import com.ade.kotlin.fragment.PemainTeamFragment

class TeamPagerAdapter(private val idTeam: String, fm: FragmentManager, private var tabCount: Int)
    : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                newInstance(idTeam)
            }
            1 -> {
                newInstancePlayer(idTeam)
            }
            else -> {
                return PemainTeamFragment()
            }
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Deskripsi"
            else -> {
                return "Pemain"
            }
        }
    }

    companion object {
        const val KEY_TEAM = "KEY_TEAM"
        const val KEY_TEAM_2 = "KEY_TEAM_2"
        fun newInstance(id: String): DescTeamFragment {
            val bindData = Bundle()
            bindData.putString(KEY_TEAM, id)

            val desciptionTeamFragment = DescTeamFragment()
            desciptionTeamFragment.arguments = bindData
            return desciptionTeamFragment
        }

        fun newInstancePlayer(id: String): PemainTeamFragment {
            val bindData = Bundle()
            bindData.putString(KEY_TEAM_2, id)

            val playerTeamFragment = PemainTeamFragment()
            playerTeamFragment.arguments = bindData
            return playerTeamFragment
        }
    }
}

