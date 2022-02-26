package wood.mike

/**
 * Interceptor named same as controller automatically picks up requests
 */
class ChickenInterceptor {

    def chickenService

    boolean before() {
        log.info "Chicken has been asked to ${params.action}"
        if( params.action == 'eat' && !params.food ) {
            params.food = chickenService.randomFood()
        }
        true
    }

    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }
}
