package wood.mike

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * Testing various flavours of getting data the normal way and by modifying the metaclass on the domain
 */
class ChickenControllerSpec extends Specification implements ControllerUnitTest<ChickenController>, DataTest{

    void setupSpec() {
        mockDomains Chicken
    }

    def setup() {
    }

    def cleanup() {
    }

    void "test findChickenService"() {
        given:
            def name = 'wes'
            new Chicken(name: name).save()
            controller.chickenService = new ChickenService()
        when:
            def found = controller.findChickenService(name)
        then:
            found.name == name
    }

    @ConfineMetaClassChanges(Chicken)
    void "test findChickenService metaclass" () {
        given:
            def originalName = 'brian'
            new Chicken(name: originalName).save()
            def newName = 'zelda'
            controller.chickenService = new ChickenService()
            Chicken.metaClass.static.findByName = { name -> new Chicken( name: newName ) }
        when:
            def found = controller.findChickenService(originalName)
        then:
            found.name == newName
    }

    void "test findChickenDomain"() {
        given:
            def name = 'axl'
            new Chicken(name: name).save()
        when:
            def found = controller.findChickenDomain(name)
        then:
            found.name == name
    }

    @ConfineMetaClassChanges(Chicken)
    void "test findChickenDomain metaclass"() {
        given:
            def originalName = 'wendy'
            def newName = 'simon'
            new Chicken(name: originalName).save()
            Chicken.metaClass.static.findByName = { name -> new Chicken( name: newName ) }
        when:
            def found = controller.findChickenDomain(originalName)
        then:
            found.name == newName
    }
}
