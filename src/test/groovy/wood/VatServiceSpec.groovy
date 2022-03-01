package wood

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import spock.lang.Unroll
import wood.mike.VatService

class VatServiceSpec extends Specification implements ServiceUnitTest<VatService> {

    @Unroll
    def "#memberState : #vatNumber #unrollDescription"(String memberState, String vatNumber, Boolean expected, String unrollDescription) {

        expect:
        expected == service.validateVat(memberState, vatNumber)

        where:
        memberState | vatNumber   || expected
        'es'        | 'B99286353' || true
        'es'        | 'B19280031' || true
        'es'        | 'XXXXXXXXX' || false

        unrollDescription = expected ? 'is valid' : ' is not valid'
    }
}

