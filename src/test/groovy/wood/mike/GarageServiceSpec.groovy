package wood.mike

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class GarageServiceSpec extends Specification implements ServiceUnitTest<GarageService>, DataTest{

    void setupSpec() {
        mockDomains CarManufacturer, Car, Garage
    }

    def setup() {
    }

    def cleanup() {
    }

    void "test findByManufacturerIds"() {
        given:
            CarManufacturer ford  = new CarManufacturer(name: 'Ford', established: new Date()).save()
            Car car = new Car(manufacturer: ford, model: 'Fiesta', engineSize: 1.0, releaseDate: new Date()).save()
            new Garage(car: car).save()
            def manufacturerIds = [ford.id, 200l, 40010l]
        when:
            def res = service.findByManufacturerIds( manufacturerIds )
        then:
            1 == Garage.all.size()
        and:
            res[0].car == car
    }

    void "test findByManufacturerIdsDifferent"() {
        given:
            CarManufacturer ford  = new CarManufacturer(name: 'Ford', established: new Date()).save()
            Car car = new Car(manufacturer: ford, model: 'Fiesta', engineSize: 1.0, releaseDate: new Date()).save()
            new Garage(car: car).save()
            def manufacturerIds = [ford.id, 200l, 40010l]
        when:
            def res = service.findByManufacturerIdsDifferent( manufacturerIds )
        then:
            1 == Garage.all.size()
        and:
           res[0].car == car
    }
}
