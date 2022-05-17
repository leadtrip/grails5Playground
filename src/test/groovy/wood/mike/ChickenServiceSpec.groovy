package wood.mike

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/*
 * Testing various flavours of getting data the normal way and by modifying the metaclass on the domain
 */
class ChickenServiceSpec extends Specification implements ServiceUnitTest<ChickenService>, DataTest{

    def setup() {
        mockDomains Chicken
    }

    void "test create"() {
        when:
            def nigel = service.create( 'nigel' )
        then:
            nigel
    }

    void "test delete and get service"() {
        when:
            def bob = service.create( 'bob' )
        then:
            bob
        and:
            !service.delete( bob )
        and:
            !service.findByName( 'bob' )
    }

    void "test delete then get using domain in test"() {
        when:
            def jim = service.create( 'jim' )
        then:
            jim
        and:
            Chicken.get( jim.id )
            1 == Chicken.all.size()
        and:
            !service.delete( jim )
        and:
            !service.findByName( jim.name )
        and:
            0 == Chicken.all.size()
        and:
            !Chicken.get( jim.id )
            !Chicken.all
    }

    void "create in test and get with service"() {
        when:
            def carol = new Chicken( name: 'carol' ).save()
        then:
            service.findByName( carol.name )
        and:
            !carol.delete()
        and:
            !service.findByName( carol.name )
        and:
            !Chicken.all
    }

    @ConfineMetaClassChanges(Chicken)
    void "test modifiying domain metaclass"() {
        given:
            def originalName = 'susan'
            new Chicken(name: originalName ).save()
            def newName = 'barry'
            Chicken.metaClass.static.findByName = { name -> new Chicken( name: newName ) }
        when:
            def found = service.findByName( originalName )
        then:
            found.name == newName
    }

    void "test findChickensCriteria"() {
        given:
            def brian = new Chicken(name: 'brian').save()
            def geoff = new Chicken(name: 'geoff').save()
            def debbie = new Chicken(name: 'debbie').save()
            def sue = new Chicken(name: 'sue').save()
        when:
            def foundChickens = service.findChickensCriteria([brian.name, debbie.name])
        then:
            foundChickens.size == 2
            foundChickens.contains(brian)
            foundChickens.contains(debbie)
    }

    void "test allChickensCriteria"() {
        given:
            new Chicken(name: 'brian').save()
            new Chicken(name: 'geoff').save()
            new Chicken(name: 'debbie').save()
            new Chicken(name: 'sue').save()
        when:
            def foundChickens = service.allChickensCriteria()
        then:
            foundChickens == [[1, 'brian'], [2, 'geoff'], [3, 'debbie'], [4, 'sue']]
    }
}
