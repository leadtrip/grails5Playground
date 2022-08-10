package wood.mike

import grails.gorm.transactions.Transactional
import org.springframework.transaction.annotation.Propagation


class PropagationTestService {

    final static String BOOK_TITLE = 'Groovy for Domain-Specific Languages'

    @Transactional(propagation = Propagation.REQUIRED)
    def propagationRequired() {
        def book = Book.findByTitle(BOOK_TITLE)
        [isTransNew: transactionStatus.isNewTransaction(),
        dbResult: book]
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    def propagationSupports() {
        def book = Book.findByTitle(BOOK_TITLE)
        [isTransNew: transactionStatus.isNewTransaction(),
         dbResult: book]
    }

    @Transactional(propagation = Propagation.MANDATORY)
    def propagationMandatory() {
        def book = Book.findByTitle(BOOK_TITLE)
        [isTransNew: transactionStatus.isNewTransaction(),
         dbResult: book]
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    def propagationRequiresNew() {
        def book = Book.findByTitle(BOOK_TITLE)
        [isTransNew: transactionStatus.isNewTransaction(),
         dbResult: book]
    }

    @Transactional(propagation = Propagation.NEVER)
    def propagationNever() {
        def book = Book.findByTitle(BOOK_TITLE)
        [isTransNew: transactionStatus.isNewTransaction(),
         dbResult: book]
    }

    @Transactional(propagation = Propagation.NESTED)
    def propagationNested() {
        def book = Book.findByTitle(BOOK_TITLE)
        [isTransNew: transactionStatus.isNewTransaction(),
         dbResult: book]
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    def propagationNotSupported() {
        def book
        try {
            book = Book.findByTitle(BOOK_TITLE)
        }
        catch(Exception e) {
            [isTransNew: transactionStatus.isNewTransaction(),
             dbResult  : book ?: e.message]
        }
    }
}
