package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class GarageService {

    def findByManufacturerIds( List<Long> manufacturerIds ) {
        Garage.createCriteria().list {
            car{
                manufacturer {
                    'in'( 'id', manufacturerIds )
                }
            }
        }
    }

    def findByManufacturerIdsDifferent( List<Long> manufacturerIds ) {
        Garage.createCriteria().list {
            car{
                'in'( 'manufacturer', CarManufacturer.findAllById( manufacturerIds ) )
            }
        }
    }
}
