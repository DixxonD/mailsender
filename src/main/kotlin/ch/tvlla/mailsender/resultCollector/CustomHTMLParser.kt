package ch.tvlla.mailsender.resultCollector

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.*


data class Event(
    val description: String,
    val date: String,
    val id: String,
)

const val BASE_PATH = "http://slv.laportal.net/Competitions/"
const val RESULT_OVERVIEW = "Resultoverview"
const val COMPETITION_TABLE = "competitiontable"

class HTMLParser(){

    companion object{

        /**
         * Returns a list with all current Events.
         */
        fun getCompetitions(): List<Event>{
            val doc = getDocument("Current")
            val resultList = mutableListOf<Event>()
            doc.getElementsByClass(COMPETITION_TABLE)
                .select("tr")
                .forEach {
                    if(it.select("a").size >= 3){
                        resultList.add(getEventFromCompetitionTable(it))
                    }
                }
            return resultList
        }

        /**
         * Returns all results of all athletes of the specified club
         */
        fun getResultsByClub(doc: Document, competition: String, club: String): List<Result>{
            val resultList = mutableListOf<Result>()
            val clubDivs = doc.getElementsContainingOwnText(club)

            for(clubDiv in clubDivs){
                val entryLine = getGrandParent(clubDiv)
                if(entryLine.isEmpty) continue
                val discipline = getDisciplineFromEntryLine(entryLine.get())
                val result = getResultsFromEntryLine(entryLine.get(), discipline, competition)
                resultList.add(result)
            }
            return resultList
        }

        /**
         * Returns the Jsoup-HTML representation of a website
         */
        fun getDocument(qualifier: String, id: String = ""): Document =
            Jsoup
                .connect("$BASE_PATH$qualifier/$id")
                .maxBodySize(0)
                .get()

        private fun getEventFromCompetitionTable(element: Element): Event{
            val a = element.select("a")
            val description = "${a[1].text()}, ${a[2].text()}"
            val path = element.select("a").attr("href")
            val time = element.select("time").text()
            return Event(description, time, getCompetitionID(path))
        }

        private fun getGrandParent(element: Element): Optional<Element> {
            if(element.parent() == null) return Optional.empty()
            val grandparent = element.parent()?.parent() ?: return Optional.empty()
            return Optional.of(grandparent)
        }

        private fun getDisciplineFromEntryLine(element: Element): String{
            val resultBlock = getGrandParent(element)
            if(resultBlock.isEmpty) return "unknown discipline"
            val runBlock = resultBlock.get().parent() ?: return "unknown discipline"
            val listHeader = runBlock.previousElementSibling()
            return listHeader?.select("a")?.text() ?: return "unknown discipline"
        }

        private fun getCompetitionID(path: String) = path.split("/").last()

        private fun getResultsFromEntryLine(entryLine: Element, discipline: String, competition: String): Result{
            val name = getDataFromResultBlock(entryLine, "col-2", "firstline")
            val result = getDataFromResultBlock(entryLine, "col-4", "firstline")
            val info = getDataFromResultBlock(entryLine, "col-last", "firstline")
            return Result(0, result, name, discipline, competition, info, LocalDateTime.now())
        }

        private fun getDataFromResultBlock(entryLine: Element, colKey: String, lineKey: String) : String {
         return entryLine.getElementsByClass(colKey).first()?.getElementsByClass(lineKey)?.text() ?:  "unknown"
        }

    }

}