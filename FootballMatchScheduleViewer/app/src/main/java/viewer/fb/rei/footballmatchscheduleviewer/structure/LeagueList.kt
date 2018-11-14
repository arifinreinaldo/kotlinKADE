package viewer.fb.rei.footballmatchscheduleviewer.structure

/**
 * Created by sapuser on 11/7/2018.
 */
data class LeagueList(
    val leagues: List<League>
)

data class League(
    val idLeague: String,
    val strLeague: String,
    val strLeagueAlternate: String,
    val strSport: String
)