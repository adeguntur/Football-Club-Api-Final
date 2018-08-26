package com.ade.kotlin.db

data class FavoriteMatch(
        val id: Long? = null,
        val idEvent: String? = null,
        val strHomeTeam: String? = null,
        val strAwayTeam: String? = null,
        val intHomeScore: String? = null,
        val intAwayScore: String? = null,
        val dateEvent: String? = null,
        val strHomeLineupGoalkeeper: String? = null,
        val strAwayLineupGoalkeeper: String? = null,
        val strHomeGoalDetails: String? = null,
        val strAwayGoalDetails: String? = null,
        val intHomeShots: String? = null,
        val intAwayShots: String? = null,
        val strHomeLineupDefense: String? = null,
        val awayDefense: String? = null,
        val strAwayLineupDefense: String? = null,
        val strAwayLineupMidfield: String? = null,
        val strHomeLineupForward: String? = null,
        val strAwayLineupForward: String? = null,
        val strHomeLineupSubstitutes: String? = null,
        val strAwayLineupSubstitutes: String? = null,
        val strHomeFormation: String? = null,
        val strAwayFormation: String? = null,
        val strTeamBadge: String? = null,
        val idHomeTeam: String? = null,
        val idAwayTeam: String? = null) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val HOME_TEAM_STR: String = "HOME_TEAM_STR"
        const val AWAY_TEAM_STR: String = "STR_AWAY_TEAM_STR"
        const val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val HOME_LINEUP_GOALKEEPER_STR: String = "HOME_LINEUP_GOALKEEPER_STR"
        const val AWAY_LINEUP_GOALKEEPER_STR: String = "AWAY_LINEUP_GOALKEEPER_STR"
        const val HOME_GOAL_DETAILS_STR: String = "HOME_GOAL_DETAILS_STR"
        const val AWAY_GOAL_DETAILS_STR: String = "AWAY_GOAL_DETAILS_STR"
        const val INT_HOME_SHOTS: String = "INT_HOME_SHOTS"
        const val INT_AWAY_SHOTS: String = "INT_AWAY_SHOTS"
        const val HOME_LINEUP_DEFENSE_STR: String = "HOME_LINEUP_DEFENSE_STR"
        const val AWAY_DEFENSE: String = "AWAY_DEFENSE"
        const val AWAY_LINEUP_DEFENSE_STR: String = "AWAY_LINEUP_DEFENSE_STR"
        const val AWAY_LINEUP_MIDFIELD_STR: String = "AWAY_LINEUP_MIDFIELD_STR"
        const val HOME_LINEUP_FORWARD_STR: String = "HOME_LINEUP_FORWARD_STR"
        const val AWAY_LINEUP_FORWARD_STR: String = "AWAY_LINEUP_FORWARD_STR"
        const val HOME_LINEUP_SUBSTITUTES_STR: String = "HOME_LINEUP_SUBSTITUTES_STR"
        const val AWAY_LINEUP_SUBSTITUTES_STR: String = "AWAY_LINEUP_SUBSTITUTES_STR"
        const val HOME_FORMATION_STR: String = "HOME_FORMATION_STR"
        const val AWAY_FORMATION_STR: String = "AWAY_FORMATION_STR"
        const val TEAM_BADGE_STR: String = "TEAM_BADGE_STR"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
    }
}
