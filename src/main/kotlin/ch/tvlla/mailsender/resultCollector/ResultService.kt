package ch.tvlla.mailsender.resultCollector

import org.jobrunr.scheduling.JobScheduler
import org.jobrunr.scheduling.cron.Cron
import org.springframework.stereotype.Service

@Service
class ResultService(private val jobScheduler: JobScheduler, private val resultCollector: ResultCollector) {

    fun startCollecting(){
        jobScheduler.scheduleRecurrently(Cron.every5minutes()) { resultCollector.collectResults() }
    }

    fun getResults(): List<Result> {
        return resultCollector.getCollectedData()
    }

}
