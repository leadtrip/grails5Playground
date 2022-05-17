package wood.mike

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification
import wood.mike.ChickenInterceptor

class ChickenInterceptorSpec extends Specification implements InterceptorUnitTest<ChickenInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test chicken interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"chicken")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
