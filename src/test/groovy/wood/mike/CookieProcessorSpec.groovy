package wood.mike

import grails.converters.JSON
import org.apache.tomcat.util.http.LegacyCookieProcessor
import org.apache.tomcat.util.http.Rfc6265CookieProcessor
import org.grails.web.json.JSONObject
import spock.lang.Specification

import javax.servlet.http.Cookie

class CookieProcessorSpec extends Specification {

    void "test cookie encoding decoding"() {
        given:
            def jsonString = new JSONObject(getSomeJson()).toString()
            def base64Json = jsonString.bytes.encodeBase64().toString()
        when:
            def cookie = new Cookie('myCookie', base64Json )
        and:
            def cookieToString = new String( cookie.value.decodeBase64() )
            def cookieToJson = JSON.parse(cookieToString)
        then:
            cookieToString == jsonString
            cookieToJson.allocationIds == [5]
            cookieToJson.numberOfRooms == 1
            !cookieToJson.numberOfAdults
    }

    void "test LegacyCookieProcessor with json is fine with json" () {
        given:
            def jsonString = new JSONObject(getSomeJson()).toString()
        and:
            def processor = new LegacyCookieProcessor()
        when:
            def cookie = new Cookie('myCookie', jsonString )
        then:
           processor.generateHeader(cookie)
    }

    void "test Rfc6265CookieProcessor with json fails due to invalid double quote char" () {
        given:
            def jsonString = new JSONObject(getSomeJson()).toString()
        and:
            def processor = new Rfc6265CookieProcessor()
            def cookie = new Cookie('myCookie', jsonString )
        when:
            processor.generateHeader(cookie)
        then:
            def iae = thrown(IllegalArgumentException)
            iae.message == 'An invalid character [34] was present in the Cookie value'
    }

    void "test Rfc6265CookieProcessor base64 encoded works" () {
        given:
            def jsonString = new JSONObject(getSomeJson()).toString()
            def base64Json = jsonString.bytes.encodeBase64().toString()
        and:
            def processor = new Rfc6265CookieProcessor()
        when:
            def cookie = new Cookie('myCookie', base64Json )
        then:
           processor.generateHeader(cookie)
    }

    def getSomeJson() {
        def cmd = [:]
        cmd.allocationIds = [5]
        cmd.numberOfRooms = 1
        cmd.bookingType = 'book'
        cmd.numberOfAdults = null
        cmd.numberOfChildren = null
        cmd.numberOfInfants = null
        cmd.cachedDataId = null
        cmd.bookingId = null
        cmd
    }
}
