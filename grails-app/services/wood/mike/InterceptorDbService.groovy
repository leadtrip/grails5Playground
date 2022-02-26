package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class InterceptorDbService {

    def doSomethingInDb() {
        log.info "Doing something in the database"
    }
}
