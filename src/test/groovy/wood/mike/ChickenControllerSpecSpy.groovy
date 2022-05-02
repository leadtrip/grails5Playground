package wood.mike

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

/**
 * A spy is always based on a real object.
 * You can either let it call real methods or stub, examples of both below
 */
class ChickenControllerSpecSpy extends Specification implements ControllerUnitTest<ChickenController>, DataTest{

    void setupSpec() {
        mockDomains Chicken
    }

    void "test with chickenService as a Spy"() {
        given:
            def chickenName = 'Albert'
            def chickenService = Spy(ChickenService)
            controller.chickenService = chickenService
            new Chicken( name: chickenName ).save()
        when:
            def found = controller.findChickenService( chickenName )
        then:
            1 * chickenService.findByName(_)    // all we're doing here is listening in on the conversation & not making any changes to the action/response
            found.name == chickenName
        when:
            controller.delete(found)
        then:
            1 * chickenService.delete(_)        // just listening again
            !controller.findChickenService( chickenName )
    }

    void "test with chickenService as Spy with some stubbing"() {
            def chickenName = 'Rod'
            def newName = 'Barbara'
            def chickenService = Spy(ChickenService)
            controller.chickenService = chickenService
            new Chicken( name: chickenName ).save()
        when:
            def found = controller.findChickenService( chickenName )
        then:
            chickenService.findByName(_) >> new Chicken(name:  newName)     // stub the response from the service
            found.name == newName
        when:
            controller.delete(found)
        then:
            1 * chickenService.delete(_)                    // call the real delete on the service
            controller.findChickenService( chickenName )    // original chicken was not deleted as we mocked the response
    }

    void "test with Spy private method"() {
            def chickenName = 'Larry'
            def chickenService = Spy(ChickenService)
            controller.chickenService = chickenService
        when:
            def foundName = controller.getChickenName( new Chicken( name:  chickenName ) )
        then:
            foundName == chickenName
    }
}
