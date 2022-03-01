package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class BookPageViewDataService {

    @Transactional
    void increment(Long bookId, String bookName) {
        BookPageView views = BookPageView.findByBookId(bookId)
        if (!views) {
            new BookPageView( bookId: bookId, bookName: bookName, views: 1 ).save(failOnError: true)
        } else {
            views.views++
            views.save(failOnError: true)
        }
    }
}
