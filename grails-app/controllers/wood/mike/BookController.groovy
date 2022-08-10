package wood.mike

import groovy.transform.CompileStatic
import org.springframework.http.HttpStatus

class BookController {

    static allowedMethods = [index: 'GET', show: 'GET']

    BookDataService bookDataService

    def index() {
        [bookList: bookDataService.findAll()]
    }

    def show(Long id) {
        [bookInstance: bookDataService.findById(id)]
    }

    // THIS IS A DUMB METHOD ADDED TO TEST MAKING CHANGES TO METACLASS, SEE BookControllerSpec
    def getABook(Long id) {
        Book.get( id )
    }

    def create() {
        respond new Book(params)
    }

    def checkCommandValidationTransaction(BookCommand bookCommand) {
        if(bookCommand.hasErrors()) {
            println "BookCommand has errors ${bookCommand.errors}"
        }

        def book = bookDataService.save(bookCommand.title, bookCommand.author, bookCommand.about, bookCommand.href, bookCommand.image)

        respond book
    }
}
