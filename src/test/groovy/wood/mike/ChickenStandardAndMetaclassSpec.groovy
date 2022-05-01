package wood.mike

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * Testing various flavours of getting data the normal way and by modifying the metaclass on the domain
 */
class ChickenStandardAndMetaclassSpec extends Specification implements DomainUnitTest<Chicken> {

    def setup() {
    }

    def cleanup() {
    }

    void "test findByName dynamic finder"() {
        when:
            new Chicken(name: 'sid').save()
        then:
            Chicken.findByName('sid')
    }

    @ConfineMetaClassChanges(Chicken)
    void "test findByName metaclass"() {
        given:
            def newName = 'barry'
            Chicken.metaClass.static.findByName = { name -> new Chicken( name: newName ) }
        when:
            new Chicken(name: 'sid').save()
        then:
            def chicken = Chicken.findByName('sid')
            chicken.name == newName
    }
}
