package wood.mike

/**
 * Interceptor not named in line with any controller must specify what controllers and actions it's interested in
 */
class MyInterceptor {

    def interceptorDbService

    /**
     * Many ways to match controller/actions
     */
    MyInterceptor() {
        match controller: 'first', action: 'alpha'
        match controller: 'second', action: 'beta'
        // match controller: 'second', actionName: ~/(alpha|beta)/
        // match uri: '/foo/**'
    }

    boolean before() {
        log.info "In the interceptor for ${params.controller} - ${params.action}"
        if ( params.dontDoIt ) {
            log.info( "Okay I'm not going to do it" )
            render "Interceptor handled request"
            return false
        }
        true
    }

    boolean after() {
        interceptorDbService.doSomethingInDb()
        true
    }

    void afterView() {
        // no-op
    }
}
