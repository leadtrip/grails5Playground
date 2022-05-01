package wood.mike

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

class BookControllerStandardAndMetaclassSpec extends Specification implements ControllerUnitTest<BookController> , DataTest{

    void setupSpec() {
        mockDomains Book
    }

    def setup() {
    }

    def cleanup() {
    }

    void "test getABook"() {
        given:
            def book = new Book(image: 'no', title: 'Dogs', author: 'Mike', about: 'Dogs again', href: 'nowhere').save()
        when:
            def res = controller.getABook( book.id )
        then:
            res.title == 'Dogs'
    }

    @ConfineMetaClassChanges([Book])
    void "test getABook metaClass"() {
        given:
            def bookTitle = 'Rope swings'
            def book = new Book(image: 'no', title: 'Dogs', author: 'Mike', about: 'Dogs again', href: 'nowhere').save()
            Book.metaClass.'static'.get = { Serializable id -> new Book( title: 'Rope swings' ) }
        when:
            def res = controller.getABook( book.id )
        then:
            Book.get book.id == bookTitle       // fine
            res.title == bookTitle      // THIS DOES NOT WORK, CHANGES TO METACLASS MADE IN TEST ARE NOT PICKED UP WHEN TESTING A CLASS THAT USES THE ALTERED CLASS
                                        // THIS DIFFERS TO MAKING CHANGES TO A CLASS AND TESTING IT IN THE SAME TEST, SEE above and BookSpec for more examples
    }
}
