package ch.tvlla.mailsender.resultCollector

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest

@Controller
class ResultController(private val request: HttpServletRequest, private val resultService: ResultService) {

    @GetMapping(value = ["/results"])
    fun getResults(model: Model): String{

        val allResults = resultService.getResults().sortedWith(compareBy({it.discipline}, {it.result}))
        val mapByCompetition = allResults.groupBy { it.competition }
        val mapByCompetitionAndDiscipline = mutableMapOf<String, Map<String, List<Result>>>()

        for(entry in mapByCompetition.entries.iterator()){
            //map< competition: String, disciplines: List<String>>
            val mapByDiscipline = entry.value.groupBy { it.discipline }
            mapByCompetitionAndDiscipline[entry.key] = mapByDiscipline
        }

        model.addAttribute("allResults", mapByCompetitionAndDiscipline)
        return "results"
    }

}

