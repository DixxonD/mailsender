package ch.tvlla.mailsender.resultCollector

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

import java.time.LocalDateTime
import javax.transaction.Transactional


@Service
class ResultCollector(private val resultRepository: ResultRepository){

    private val logger: Logger = LoggerFactory.getLogger(ResultCollector::class.java)

    fun collectResults(){
        collectResults("Länggasse")
        removeOldResults()
    }

    @Transactional
    fun collectResults(club: String){
        logger.info("Starts collecting data...")
        val competitions = HTMLParser.getCompetitions()
        val allResults = mutableListOf<Result>()
        competitions.forEach { competition ->
            val doc = HTMLParser.getDocument(RESULT_OVERVIEW, competition.id)
            allResults.addAll(HTMLParser.getResultsByClub(doc, competition.description, club))
        }
        updateDatabase(allResults)
        logger.info("Data collection finished. Found ${allResults.size} results in ${competitions.size} competitions.")
    }

    @Transactional
    fun removeOldResults(){
        logger.info("Delete old results...")
        val twoWeeksAgo = LocalDateTime.now().minusWeeks(2)
        val results = resultRepository.findByAddedAtBefore(twoWeeksAgo)
        results.forEach { resultRepository.delete(it) }
        logger.info("All old results have been deleted.")
    }

    private fun updateDatabase(results: List<Result>){
        results.filter { !resultRepository.existsByHash(it.hash) }.forEach {
            logger.info("Added new Result for ${it.name}")
            resultRepository.save(it)
        }
    }

    fun getCollectedData(): List<Result>{
        return resultRepository.findAll()

    }



}


