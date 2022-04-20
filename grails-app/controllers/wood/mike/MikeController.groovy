package wood.mike

/**
 * Demoing accessing a spring bean defined in resources.groovy
 */
class MikeController {

    def mikeBean

    def index() {
        render "Volume is: ${mikeBean.volume}, " +
                "name is: ${mikeBean.name}, " +
                "chickenService randomFood is: ${mikeBean.chickenService.randomFood()}," +
                "dog is ${mikeBean.dog.name}"
    }
}
