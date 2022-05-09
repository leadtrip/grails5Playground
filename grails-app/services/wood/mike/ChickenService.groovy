package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class ChickenService {

    def randomFood() {
        def menu = ['grain', 'worms', 'insects', 'slugs', 'beef', 'pasta', 'fish']
        menu[new Random().nextInt(menu.size())]
    }

    def create(name) {
        new Chicken(name: name).save()
    }

    def delete(chicken) {
        chicken.delete()
    }

    def findByName(name ) {
        Chicken.findByName(name)
    }

    def getChickenNamePublic( chicken ) {
        getChickenNamePrivate( chicken )
    }

    private String getChickenNamePrivate( chicken ) {
        chicken.name
    }

    def findChickensCriteria( List<String> chickenNames ) {
        Chicken.createCriteria().list {
            'in'('name', chickenNames )
        }
    }
}
