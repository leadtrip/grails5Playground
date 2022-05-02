package wood.mike

class ChickenController {

    def chickenService

    def index() {
        render "Standing"
    }

    def cluck() {
        render "Clucking"
    }

    def walk() {
        render "Walking"
    }

    def eat() {
        render "Eating ${params.food}"
    }

    def findChickenService( name ) {
        chickenService.findByName( name )
    }

    def findChickenDomain( name ) {
        Chicken.findByName( name )
    }

    def delete( chicken ) {
        chickenService.delete( chicken )
    }

    def getChickenName( chicken ) {
        chickenService.getChickenNamePublic( chicken )
    }
}
