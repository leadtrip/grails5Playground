package wood.mike

import spock.lang.Specification

class MikeCommandSpec extends Specification{

    void "test validate"() {
        given:
            MikeCommand mc = new MikeCommand(  isrn: 29123 )
        expect:
            mc.validate()
    }
}
