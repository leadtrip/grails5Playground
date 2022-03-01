package wood.mike

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class BookShowInterceptorSpec extends Specification implements InterceptorUnitTest<BookShowInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test bookShow interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"bookShow")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
