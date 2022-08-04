package wood.mike

import grails.gorm.transactions.Transactional
import org.hibernate.criterion.CriteriaSpecification

@Transactional
class ChickenService {

    def randomFood() {
        def menu = ['grain', 'worms', 'insects', 'slugs', 'beef', 'pasta', 'fish']
        menu[new Random().nextInt(menu.size())]
    }

    def create() {
        new Chicken(name: UUID.randomUUID().toString().take(8) + '_create', address: new Address(street: 'Chick lane', city: 'Chickenham')).save()
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

    /**
     * @return  a list of property values [[1, 'brian'], [2, 'geoff']]
     */
    def allChickensCriteria() {
        Chicken.createCriteria().list {
            projections{
                property("id")
                property("name")
            }
        }
    }

    /**
     * @return  a list of maps [[dbId: 1, nom: 'dave], [dbId:2, nom: 'sue']]
     */
    def allChickensCriteriaCustomPropertyNames() {
        Chicken.createCriteria().list {
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections{
                property( 'id', 'dbId' )
                property( 'name', 'nom' )
            }
        }
    }

    def all() {
        Chicken.all
    }
}
