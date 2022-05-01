package wood.mike

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class ChickenServiceStaticCompileServiceSpec extends Specification implements ServiceUnitTest<ChickenServiceStaticCompileService>, DataTest{

    void setupSpec() {
        mockDomains Chicken
    }

    void "test getChicken"() {
        when:
           def chicken = new Chicken(name: 'alan').save()
        then:
            service.getChicken( chicken.id )
    }

    /**
     * THIS TEST FAILS WITH THE @CompileStatic ANNOTATION IN ChickenServiceStaticCompileService, REMOVE THE ANNOTATION AND IT PASSES
     */
    void "test getChicken metaclass"() {
        given:
            def originalName = 'brian'
            def chicken = new Chicken(name: originalName).save()
            def newName = 'zelda'
            Chicken.metaClass.static.get = { Serializable id -> new Chicken( name: newName ) }
        when:
            def found = service.getChicken( chicken.id )
        then:
            found.name == newName
    }
}
