package wood.mike

import grails.gorm.transactions.Transactional
import groovy.time.TimeCategory

@Transactional
class IncidentService {

    def createIncidents() {
        100.times { tally ->
            def incidentDate = use(TimeCategory){ new Date() - tally.days }
            new Incident(description: UUID.randomUUID().toString().take(8), incidentDate: incidentDate).save()
        }
    }

    def findAll() {
        Incident.findAll()
    }

    def deleteOlderThan2Weeks() {
        def twoWeeksAgo = use(TimeCategory){ new Date() - 2.weeks }
        Incident.where {
            incidentDate < twoWeeksAgo
        }.deleteAll()
    }
}
