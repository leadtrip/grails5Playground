package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class RandomTwoService {

    def execute() {
        Chicken.findById(2131)
        throw new CustomException("exception")
    }
}
