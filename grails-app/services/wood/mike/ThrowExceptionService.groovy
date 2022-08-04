package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class ThrowExceptionService {

    def throwChecked() {
        new Chicken(name: UUID.randomUUID().toString().take(6) + '_checked', address: new Address(street: 'Baker', city: 'London')).save(failOnError: true)
        throw new Exception("Checked")
    }

    def throwUnchecked() {
        new Chicken(name: UUID.randomUUID().toString().take(6) + '_unchecked', address: new Address(street: 'Wessex', city: 'Bristol')).save(failOnError: true)
        throw new RuntimeException("Unchecked")
    }
}