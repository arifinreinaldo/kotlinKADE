package viewer.fb.rei.footballmatchscheduleviewer.structure

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class LeagueEventList {
    @SerializedName("events")
    @Expose
    open var events: List<LeagueEvent>? = null

}


data class LeagueEvent(
    val dateEvent: String,
    val idAwayTeam: String,
    val idEvent: String,
    val idHomeTeam: String,
    val idLeague: String,
    val idSoccerXML: String,
    val intAwayScore: String,
    val intAwayShots: Any,
    val intHomeScore: String,
    val intHomeShots: Any,
    val intRound: String,
    val intSpectators: Any,
    val strAwayFormation: Any,
    val strAwayGoalDetails: Any,
    val strAwayLineupDefense: Any,
    val strAwayLineupForward: Any,
    val strAwayLineupGoalkeeper: Any,
    val strAwayLineupMidfield: Any,
    val strAwayLineupSubstitutes: Any,
    val strAwayRedCards: Any,
    val strAwayTeam: String,
    val strAwayYellowCards: Any,
    val strBanner: Any,
    val strCircuit: Any,
    val strCity: Any,
    val strCountry: Any,
    val strDate: String,
    val strDescriptionEN: Any,
    val strEvent: String,
    val strFanart: Any,
    val strFilename: String,
    val strHomeFormation: Any,
    val strHomeGoalDetails: Any,
    val strHomeLineupDefense: Any,
    val strHomeLineupForward: Any,
    val strHomeLineupGoalkeeper: Any,
    val strHomeLineupMidfield: Any,
    val strHomeLineupSubstitutes: Any,
    val strHomeRedCards: Any,
    val strHomeTeam: String,
    val strHomeYellowCards: Any,
    val strLeague: String,
    val strLocked: String,
    val strMap: Any,
    val strPoster: Any,
    val strResult: Any,
    val strSeason: String,
    val strSport: String,
    val strTVStation: Any,
    val strThumb: String,
    val strTime: String
): Serializable