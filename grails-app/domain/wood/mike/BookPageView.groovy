package wood.mike

import grails.compiler.GrailsCompileStatic
import grails.rest.Resource

@Resource       // allows us to interact with this domain as a REST resource
@GrailsCompileStatic
class BookPageView {
    Long bookId
    String bookName
    Integer views

    static constraints = {
        bookId nullable: false, unique: true
        bookName nullable: false
        views nullable: false, min: 0
    }
}
