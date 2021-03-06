package viewer.fb.rei.footballmatchscheduleviewer.structure

import java.io.Serializable

/**
 * Created by sapuser on 11/8/2018.
 */
data class LeagueTeamMember(
        val player: List<Player>
)

data class Player(
        val dateBorn: String,
        val dateSigned: Any,
        val idPlayer: String,
        val idPlayerManager: Any,
        val idSoccerXML: String,
        val idTeam: String,
        val intLoved: String,
        val intSoccerXMLTeamID: Any,
        val strBanner: Any,
        val strBirthLocation: String,
        val strCollege: Any,
        val strCutout: Any,
        val strDescriptionCN: Any,
        val strDescriptionDE: Any,
        val strDescriptionEN: String,
        val strDescriptionES: Any,
        val strDescriptionFR: Any,
        val strDescriptionHU: Any,
        val strDescriptionIL: Any,
        val strDescriptionIT: Any,
        val strDescriptionJP: Any,
        val strDescriptionNL: Any,
        val strDescriptionNO: Any,
        val strDescriptionPL: Any,
        val strDescriptionPT: Any,
        val strDescriptionRU: Any,
        val strDescriptionSE: Any,
        val strFacebook: String,
        val strFanart1: Any,
        val strFanart2: Any,
        val strFanart3: Any,
        val strFanart4: Any,
        val strGender: String,
        val strHeight: String,
        val strInstagram: String,
        val strLocked: String,
        val strNationality: String,
        val strPlayer: String,
        val strPosition: String,
        val strSigning: String,
        val strSport: String,
        val strTeam: String,
        val strThumb: String,
        val strTwitter: String,
        val strWage: String,
        val strWebsite: String,
        val strWeight: String,
        val strYoutube: String
) : Serializable