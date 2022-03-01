package wood.mike

/**
 * Testing out interceptor functionality, check out MyInterceptor
 */
class FirstController {

    def index() {
        render link(action: 'alpha', 'Goto alpha')
    }

    def alpha() {
        render link ( action: 'beta',"rendered by FirstController.alpha, goto beta")
    }

    def beta() {
        render link ( action: 'alpha',  "rendered by FirstController.beta, goto alpha" )
    }
}
