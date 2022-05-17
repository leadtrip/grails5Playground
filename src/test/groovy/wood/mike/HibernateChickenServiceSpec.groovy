package wood.mike

import grails.gorm.transactions.Rollback
import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest

/**
 * Test the features that require hibernate specific support
 */
class HibernateChickenServiceSpec extends HibernateSpec implements ServiceUnitTest<ChickenService> {

    /**
     * allChickensCriteriaCustomPropertyNames uses a resultTransformer to get a list of maps which requires HibernateSpec
     */
    @Rollback
    void "test allChickensCriteriaCustomPropertyNames"() {
        given:
            new Chicken(name: 'brian').save()
            new Chicken(name: 'geoff').save()
            new Chicken(name: 'debbie').save()
            new Chicken(name: 'sue').save()
        when:
            def foundChickens = service.allChickensCriteriaCustomPropertyNames()
        then:
           foundChickens == [[nom: 'brian', dbId:1], [nom: 'geoff', dbId:2], [nom: 'debbie', dbId:3], [nom: 'sue', dbId:4]]
    }
}
