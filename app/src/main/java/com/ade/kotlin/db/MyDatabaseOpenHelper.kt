package com.ade.kotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx,
        "Football.db",
        null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteMatch.TABLE_FAVORITE, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.ID_EVENT to TEXT + UNIQUE,
                FavoriteMatch.HOME_TEAM_STR to TEXT,
                FavoriteMatch.AWAY_TEAM_STR to TEXT,
                FavoriteMatch.INT_HOME_SCORE to TEXT,
                FavoriteMatch.INT_AWAY_SCORE to TEXT,
                FavoriteMatch.DATE_EVENT to TEXT,
                FavoriteMatch.HOME_LINEUP_GOALKEEPER_STR to TEXT,
                FavoriteMatch.AWAY_LINEUP_GOALKEEPER_STR to TEXT,
                FavoriteMatch.HOME_GOAL_DETAILS_STR to TEXT,
                FavoriteMatch.AWAY_GOAL_DETAILS_STR to TEXT,
                FavoriteMatch.INT_HOME_SHOTS to TEXT,
                FavoriteMatch.INT_AWAY_SHOTS to TEXT,
                FavoriteMatch.HOME_LINEUP_DEFENSE_STR to TEXT,
                FavoriteMatch.AWAY_DEFENSE to TEXT,
                FavoriteMatch.AWAY_LINEUP_DEFENSE_STR to TEXT,
                FavoriteMatch.AWAY_LINEUP_MIDFIELD_STR to TEXT,
                FavoriteMatch.HOME_LINEUP_FORWARD_STR to TEXT,
                FavoriteMatch.AWAY_LINEUP_FORWARD_STR to TEXT,
                FavoriteMatch.HOME_LINEUP_SUBSTITUTES_STR to TEXT,
                FavoriteMatch.AWAY_LINEUP_SUBSTITUTES_STR to TEXT,
                FavoriteMatch.HOME_FORMATION_STR to TEXT,
                FavoriteMatch.AWAY_FORMATION_STR to TEXT,
                FavoriteMatch.TEAM_BADGE_STR to TEXT,
                FavoriteMatch.ID_HOME_TEAM to TEXT,
                FavoriteMatch.ID_AWAY_TEAM to TEXT)

        db?.createTable(FavoritesTeam.TBL_FAVORITE_TEAM, true,
                FavoritesTeam.ID_FAVORITE_TEAM to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoritesTeam.TEAM_ID to TEXT + UNIQUE,
                FavoritesTeam.TEAM_NAME to TEXT,
                FavoritesTeam.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
        db.dropTable(FavoritesTeam.TBL_FAVORITE_TEAM, true)

    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)