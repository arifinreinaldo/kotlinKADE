package viewer.fb.rei.footballmatchscheduleviewer.model

import java.io.Serializable

/**
 * Created by sapuser on 11/1/2018.
 */
data class TeamModal(val idTeam: String?, val strTeam:String?, val strTeamBadge:String?):Serializable {

    companion object {
        const val TableName: String = "t_team"
        const val idTeam: String = "idTeam"
        const val strTeam: String = "strTeam"
        const val strTeamBadge: String = "strTeamBadge"
    }

}