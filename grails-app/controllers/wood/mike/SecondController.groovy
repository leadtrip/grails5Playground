package wood.mike

/*
* Testing out interceptor functionality, check out MyInterceptor
 */
class SecondController {

    def index() {
        render link(action: 'alpha', 'Goto alpha')
    }

    def alpha() {
        render link( action: 'beta', "rendered by SecondController.alpha, goto beta" )
    }

    def beta() {
        render link( action: 'alpha', "rendered by SecondController.beta, goto alpha" )
    }
}
