package wood.mike

import grails.validation.Validateable

class MikeCommand implements Validateable{

    String thing
    String anotherThing
    Integer isrn

    static constraints = {
        thing nullable: true
        anotherThing nullable: true, validator: sillyValidator
        allOfIt nullable: true                      // without this getAllOfIt fails validation
    }

    static sillyValidator = { String s ->
        println 'In sillyValidator'
        s?.contains( 'silly' )
    }

    String getAllOfIt() {
        println 'In getAllOfIt'
        null
    }
    
    String justGetAllOfIt() {
        println 'In justGetAllOfIt'
        null
    }
}
