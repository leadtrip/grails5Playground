package wood.mike

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

import java.text.DateFormat
import java.text.SimpleDateFormat

class CarServiceSpec extends Specification implements ServiceUnitTest<CarService>, DataTest{

    void setupSpec() {
        mockDomains Car, CarManufacturer
    }

    def setup() {
    }

    def cleanup() {
    }

    void "test findAllByManufacturer"() {
        given:
            def ford = new CarManufacturer(name: 'Ford', established: new Date()).save(failOnError: true, flush: true)
            new Car( model: 'Fiesta', manufacturer: ford, engineSize: 1.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            new Car( model: 'Capri', manufacturer: ford, engineSize: 2.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            new Car( model: 'Escort', manufacturer: ford, engineSize: 1.6, releaseDate: new Date() ).save(failOnError: true, flush: true )
        when:
            def res = service.findAllByManufacturer( ford )
        then:
            res.size() == 3
    }

    void "test findAllByManufacturerName"() {
        given:
            def ford = new CarManufacturer(name: 'Ford', established: new Date()).save(failOnError: true, flush: true)
            new Car( model: 'Fiesta', manufacturer: ford, engineSize: 1.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            new Car( model: 'Capri', manufacturer: ford, engineSize: 2.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            new Car( model: 'Escort', manufacturer: ford, engineSize: 1.6, releaseDate: new Date() ).save(failOnError: true, flush: true )
        when:
            def res = service.findAllByManufacturerName( ford.name )
        then:
            res.size() == 3
    }

    void "test findAllByModel"() {
        given:
            def ford = new CarManufacturer(name: 'Ford', established: new Date()).save(failOnError: true, flush: true)
            new Car( model: 'Fiesta', manufacturer: ford, engineSize: 1.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            new Car( model: 'Capri', manufacturer: ford, engineSize: 2.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            new Car( model: 'Escort', manufacturer: ford, engineSize: 1.6, releaseDate: new Date() ).save(failOnError: true, flush: true )
        when:
            def res = service.findAllByModel( 'Fiesta' )
        then:
            res.size() == 1
            res[0].model == 'Fiesta'
    }

    void "test findAllByDateRange"() {
        given:
            def df = new SimpleDateFormat('dd/MM/yyyy')
            def ford = new CarManufacturer(name: 'Ford', established: new Date()).save(failOnError: true, flush: true)
            new Car( model: 'Fiesta', manufacturer: ford, engineSize: 1.0, releaseDate: df.parse( '01/04/1974' ) ).save(failOnError: true, flush: true )
            new Car( model: 'Capri', manufacturer: ford, engineSize: 2.0, releaseDate: df.parse( '01/02/1965' ) ).save(failOnError: true, flush: true )
            new Car( model: 'Escort', manufacturer: ford, engineSize: 1.6, releaseDate: df.parse( '01/04/1982' ) ).save(failOnError: true, flush: true )

            def renault = new CarManufacturer( name: 'Renault', established: new Date() ).save(failOnError: true, flush: true )
            new Car( model: '5 GT Turbo', manufacturer: renault, engineSize: 1.4, releaseDate: df.parse( '03/05/1990' ) ).save(failOnError: true, flush: true )
        when:
            def res = service.findAllByDateRange( df.parse( '01/04/1970' ), df.parse( '01/04/1999' ) )
        then:
            res.size() == 3
    }

    void "test findAllByManufacturerNameExcluding"() {
        given:
            def ford = new CarManufacturer(name: 'Ford', established: new Date()).save(failOnError: true, flush: true)
            def fiesta = new Car( model: 'Fiesta', manufacturer: ford, engineSize: 1.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            def capri = new Car( model: 'Capri', manufacturer: ford, engineSize: 2.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            def escort = new Car( model: 'Escort', manufacturer: ford, engineSize: 1.6, releaseDate: new Date() ).save(failOnError: true, flush: true )
            List<Long> exclusions = Arrays.asList(fiesta.id, escort.id)
        when:
            def res = service.findAllByManufacturerNameExcluding( ford.name, exclusions )
        then:
            res.size() == 1
            res[0].model == 'Capri'
    }

    void "test findAlByExcludingManufacturers"() {
        given:
            def ford = new CarManufacturer(name: 'Ford', established: new Date()).save(failOnError: true, flush: true)
            new Car( model: 'Fiesta', manufacturer: ford, engineSize: 1.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            new Car( model: 'Capri', manufacturer: ford, engineSize: 2.0, releaseDate: new Date() ).save(failOnError: true, flush: true )
            new Car( model: 'Escort', manufacturer: ford, engineSize: 1.6, releaseDate: new Date() ).save(failOnError: true, flush: true )

            def renault = new CarManufacturer( name: 'Renault', established: new Date() ).save(failOnError: true, flush: true )
            new Car( model: '5 GT Turbo', manufacturer: renault, engineSize: 1.4, releaseDate: new Date() ).save(failOnError: true, flush: true )

            List<Long> exclusions = Arrays.asList(ford.id)
        when:
            def res = service.findAlByExcludingManufacturers( exclusions )
        then:
            res.size() == 1
            res[0].model == '5 GT Turbo'
    }
}
