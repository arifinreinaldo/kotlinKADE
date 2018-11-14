package viewer.fb.rei.footballmatchscheduleviewer.model

import java.io.Serializable

/**
 * Created by sapuser on 11/1/2018.
 */
data class LeagueEventModal(val idEvent: String?,val idHomeTeam:String?,val idAwayTeam:String?,val strHomeTeam:String?, val strAwayTeam:String,val idHomeScore:String?,val idAwayScore:String?,val dateEvent:String?):Serializable {

    companion object {
        const val TableName: String = "t_league_event"
        const val dateEvent: String = "dateEvent"
        const val idAwayTeam: String = "idAwayTeam"
        const val idEvent: String = "idEvent"
        const val idHomeTeam: String = "idHomeTeam"
        const val idLeague: String = "idLeague"
        const val idSoccerXML: String = "idSoccerXML"
        const val intAwayScore: String = "intAwayScore"
        const val intAwayShots: String = "intAwayShots"
        const val intHomeScore: String = "intHomeScore"
        const val intHomeShots: String = "intHomeShots"
        const val intRound: String = "intRound"
        const val intSpectators: String = "intSpectators"
        const val strAwayFormation: String = "strAwayFormation"
        const val strAwayGoalDetails: String = "strAwayGoalDetails"
        const val strAwayLineupDefense: String = "strAwayLineupDefense"
        const val strAwayLineupForward: String = "strAwayLineupForward"
        const val strAwayLineupGoalkeeper: String = "strAwayLineupGoalkeeper"
        const val strAwayLineupMidfield: String = "strAwayLineupMidfield"
        const val strAwayLineupSubstitutes: String = "strAwayLineupSubstitutes"
        const val strAwayRedCards: String = "strAwayRedCards"
        const val strAwayTeam: String = "strAwayTeam"
        const val strAwayYellowCards: String = "strAwayYellowCards"
        const val strBanner: String = "strBanner"
        const val strCircuit: String = "strCircuit"
        const val strCity: String = "strCity"
        const val strCountry: String = "strCountry"
        const val strDate: String = "strDate"
        const val strDescriptionEN: String = "strDescriptionEN"
        const val strEvent: String = "strEvent"
        const val strFanart: String = "strFanart"
        const val strFilename: String = "strFilename"
        const val strHomeFormation: String = "strHomeFormation"
        const val strHomeGoalDetails: String = "strHomeGoalDetails"
        const val strHomeLineupDefense: String = "strHomeLineupDefense"
        const val strHomeLineupForward: String = "strHomeLineupForward"
        const val strHomeLineupGoalkeeper: String = "strHomeLineupGoalkeeper"
        const val strHomeLineupMidfield: String = "strHomeLineupMidfield"
        const val strHomeLineupSubstitutes: String = "strHomeLineupSubstitutes"
        const val strHomeRedCards: String = "strHomeRedCards"
        const val strHomeTeam: String = "strHomeTeam"
        const val strHomeYellowCards: String = "strHomeYellowCards"
        const val strLeague: String = "strLeague"
        const val strLocked: String = "strLocked"
        const val strMap: String = "strMap"
        const val strPoster: String = "strPoster"
        const val strResult: String = "strResult"
        const val strSeason: String = "strSeason"
        const val strSport: String = "strSport"
        const val strTVStation: String = "strTVStation"
        const val strThumb: String = "strThumb"
        const val strTime: String = "strTime"
//        fun getData(): Array<out Pair<String, SqlType>> {
//        }
    }

}