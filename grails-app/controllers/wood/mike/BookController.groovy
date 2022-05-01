package wood.mike

import groovy.transform.CompileStatic

@CompileStatic
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

}
