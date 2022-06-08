package wood.mike

import grails.plugins.export.ExportService
import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

/**
 * The exportService does not like being mocked by spock in normal manner, following is a way to test without using spock
 */
class ExportControllerSpec extends Specification implements ControllerUnitTest<ExportController>, DataTest{

    def exportService

    void setupSpec() {
        mockDomains Book, Player
    }

    def setup() {}

    void "test exportBooks"() {
        given:
            def exported = false
            exportService = [export: {String type, OutputStream outputStream, List objects, Map formatters, Map parameters -> exported = true }] as ExportService
            controller.exportService = exportService
        when:
            controller.exportBooks()
        then:
            exported
    }

    void "test exportPlayers"() {
        given:
            def exported = false
            exportService = [export: {String type, OutputStream outputStream, List objects, List fields, Map labels, Map formatters, Map parameters -> exported = true}] as ExportService
            controller.exportService = exportService
        when:
            controller.exportPlayers()
        then:
            exported
    }
}
