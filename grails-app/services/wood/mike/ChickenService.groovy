package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class ChickenService {

    def randomFood() {
        def menu = ['grain', 'worms', 'insects', 'slugs', 'beef', 'pasta', 'fish']
        menu[new Random().nextInt(menu.size())]
    }
}
