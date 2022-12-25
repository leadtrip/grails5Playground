package wood.mike

import grails.web.Action

class IncidentController {

    def incidentService

    def index() {
        def actions =
                getClass().methods.toList()
                        .stream()
                        .filter(m -> m.getAnnotation(Action) != null)
                        .map(m-> m.name)
        [actions: actions, allIncidents: incidentService.findAll()]
    }

    def createIncidents() {
        incidentService.createIncidents()
        redirect action: 'index'
    }

    def deleteOlderThan2Weeks() {
        incidentService.deleteOlderThan2Weeks()
        redirect action: 'index'
    }
}
