package wood.mike

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import wslite.soap.SOAPClient
import wslite.soap.SOAPResponse

@CompileStatic
class VatService {
    String url = 'http://ec.europa.eu/taxation_customs/vies/checkVatTestService'
    SOAPClient client = new SOAPClient("${url}.wsdl")

    /**
     * THIS DOESN'T WORK, LOOKS LIKE WSDL/SERVICE HAS MOVED
     */
    @CompileDynamic
    Boolean validateVat(String memberStateCode, String vatNumberCode) {
        SOAPResponse response = client.send(SOAPAction: 'http://ec.europa.eu/taxation_customs/vies/services/checkVatTestService') {
            body('xmlns': 'urn:ec.europa.eu:taxud:vies:services:checkVat:types') {
                checkVat {
                    countryCode(memberStateCode)
                    vatNumber(vatNumberCode)
                }
            }
        }
        response.checkVatResponse.valid.text() == 'true'
    }
}
