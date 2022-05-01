package wood.mike

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

@Transactional
@CompileStatic          // WITH THIS ANNOTATION, THE TEST IN ChickenServiceStaticCompileService THAT ALTERS THE METACLASS WITH FAIL, REMOVE IT AND IT PASSES
class ChickenServiceStaticCompileService {

    def getChicken( Serializable id ) {
        Chicken.get( id )
    }
}
