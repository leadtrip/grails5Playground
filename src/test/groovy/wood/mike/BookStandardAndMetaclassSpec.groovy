package wood.mike

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

class BookSpec extends Specification implements DomainUnitTest<Book> {

    def setup() {
    }

    def cleanup() {
    }

    void "test get normal dynamic finder"() {
        given:
            def book = new Book(image: 'no', title: 'Dogs', author: 'Mike', about: 'Dogs again', href: 'nowhere').save()
        when:
            def res = Book.get( book.id )
        then:
           res.title == 'Dogs'
    }

    @ConfineMetaClassChanges([Book])
    void "test get metaClass"() {
        given:
            def bookTitle = 'Wheelbarrow'
            def book = new Book(image: 'no', title: 'Dogs', author: 'Mike', about: 'Dogs again', href: 'nowhere').save()
            Book.metaClass.'static'.get = { Serializable id -> new Book( title: bookTitle ) }
        when:
            def res = Book.get( book.id )
        then:
            res.title == bookTitle
    }

    void "test findByTitle normal dynamic finder"() {
        given:
            new Book(image: 'no', title: 'Dogs', author: 'Mike', about: 'Dogs again', href: 'nowhere').save()
        when:
            def res = Book.findByTitle( 'Dogs' )
        then:
            res
    }

    @ConfineMetaClassChanges([Book])
    void "test findByTitle metaClass"() {
        given:
            def bookTitle = 'A totally different book'
            new Book(image: 'no', title: 'Dogs', author: 'Mike', about: 'Dogs again', href: 'nowhere').save()
            Book.metaClass.'static'.findByTitle = { new Book( title: bookTitle ) }
        when:
            def res = Book.findByTitle( 'Dogs' )
        then:
           res?.title == bookTitle
    }

    void "test findAllByAuthor normal dynamic finder"() {
        given:
            new Book(image: 'no', title: 'Chips', author: 'Mike', about: 'About chips', href: 'nowhere').save()
            new Book(image: 'no', title: 'Bacon', author: 'Mike', about: 'About bacon', href: 'nowhere').save()
            new Book(image: 'no', title: 'Eggs', author: 'Dave', about: 'About eggs', href: 'nowhere').save()
        when:
            def books = Book.findAllByAuthor( 'Mike' )
        then:
            books.size() == 2
    }

    @ConfineMetaClassChanges([Book])
    void "test findAllByAuthor metaClass"() {
        given:
            new Book(image: 'no', title: 'Hills', author: 'Chris', about: 'About hills', href: 'nowhere').save()
            new Book(image: 'no', title: 'Lakes', author: 'Bob', about: 'About lakes', href: 'nowhere').save()
            new Book(image: 'no', title: 'Chimneys', author: 'Sean', about: 'About chimneys', href: 'nowhere').save()

            Book.metaClass.static.findAllByAuthor = { [new Book( title: 'Smoking', author: 'Jack' ),
                                                         new Book( title: 'Surfing', author: 'Brenda' ),
                                                         new Book( title: 'Jumping', author: 'Sid' )] }
        when:
            def books = Book.findAllByAuthor( 'Gandalf' )
        then:
            books.size() == 3
            books.find { it.title == 'Surfing' }.author == 'Brenda'
    }
}
