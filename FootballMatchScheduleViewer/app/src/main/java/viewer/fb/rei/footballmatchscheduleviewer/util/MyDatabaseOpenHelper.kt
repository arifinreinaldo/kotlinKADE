package viewer.fb.rei.footballmatchscheduleviewer.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import viewer.fb.rei.footballmatchscheduleviewer.model.LeagueEventModal
import viewer.fb.rei.footballmatchscheduleviewer.model.TeamModal

/**
 * Created by sapuser on 10/31/2018.
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, dbName, null, 1) {
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

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(LeagueEventModal.TableName, true,
                LeagueEventModal.idEvent to TEXT + PRIMARY_KEY,
                LeagueEventModal.idHomeTeam to TEXT,
                LeagueEventModal.idAwayTeam to TEXT,
                LeagueEventModal.strHomeTeam to TEXT,
                LeagueEventModal.strAwayTeam to TEXT,
                LeagueEventModal.intHomeScore to TEXT,
                LeagueEventModal.intAwayScore to TEXT,
                LeagueEventModal.dateEvent to TEXT
        )
        db.createTable(TeamModal.TableName, true,
                TeamModal.idTeam to TEXT + PRIMARY_KEY,
                TeamModal.strTeam to TEXT,
                TeamModal.strTeamBadge to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(LeagueEventModal.TableName, true)
        db.dropTable(TeamModal.TableName, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)