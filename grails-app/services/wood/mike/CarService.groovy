package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class CarService {

    def findAllByManufacturer( CarManufacturer mfr ) {
        Car.createCriteria().list {
            eq('manufacturer', mfr )
        }
    }

    def findAllByManufacturerName( String mfrName ) {
        Car.createCriteria().list {
            manufacturer {
                eq('name', mfrName )
            }
        }
    }

    def findAllByModel( String model ) {
        Car.createCriteria().list {
            eq('model', model )
        }
    }

    def findAllByDateRange( Date from, Date to ) {
        Car.createCriteria().list {
            gt('releaseDate', from )
            lt('releaseDate', to )
        }
    }

    def findAllByManufacturerNameExcluding( String mfrName, List<Long> exclusions ) {
        Car.createCriteria().list {
            manufacturer {
                eq('name', mfrName)
            }
            not {'in'( 'id', exclusions )}
        }
    }

    def findAlByExcludingManufacturers( List<Long> exludedManufacturers ) {
        Car.createCriteria().list {
            manufacturer {
                not {'in'( 'id', exludedManufacturers )}
            }
        }
    }
}
