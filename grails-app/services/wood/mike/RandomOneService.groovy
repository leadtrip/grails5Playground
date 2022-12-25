package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class RandomOneService {

    def randomTwoService

    def execute() {
        try {
            randomTwoService.execute()
        } catch(CustomException ce) {
            // unreachable code
            throw ce
        }
    }
}
