package viewer.fb.rei.footballmatchscheduleviewer.api

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueEventList
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueList
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueTeam
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueTeamMember
import viewer.fb.rei.footballmatchscheduleviewer.util.baseUrl

interface Service {
    @GET("{api_key}/{function}.php")
    fun getLeagueSchedule(@Path("api_key") api_key:String,
                          @Path("function")function:String,
                          @Query("id") id: String ): Observable<LeagueEventList>
    @GET("{api_key}/lookupevent.php")
    fun getLookUpEvent(@Path("api_key") api_key:String,@Query("id") id: String):Observable<LeagueEventList>
    @GET("{api_key}/lookupteam.php")
    fun getLookUpTeam(@Path("api_key") api_key:String,@Query("id") id: String):Observable<LeagueTeam>
    @GET("{api_key}/all_leagues.php")
    fun getAllLeague(@Path("api_key") api_key:String):Observable<LeagueList>
    @GET("{api_key}/search_all_teams.php")
    fun getTeamByLeague(@Path("api_key") api_key:String,@Query("l") l: String):Observable<LeagueTeam>
    @GET("{api_key}/lookup_all_players.php")
    fun getAllPlayerByTeam(@Path("api_key") api_key:String,@Query("id") id: String):Observable<LeagueTeamMember>

    companion object {
        fun create(): Service {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()

            return retrofit.create(Service::class.java)
        }
    }
}