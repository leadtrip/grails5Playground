package wood.mike

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class InterceptorDbServiceSpec extends Specification implements ServiceUnitTest<InterceptorDbService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
