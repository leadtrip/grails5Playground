package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class ThrowExceptionService {

    def throwChecked() {
        throw new Exception("Checked")
    }

    def throwUnchecked() {
        throw new RuntimeException("Unchecked")
    }
}