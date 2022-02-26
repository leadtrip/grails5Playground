package wood.mike

class ChickenController {

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
}
