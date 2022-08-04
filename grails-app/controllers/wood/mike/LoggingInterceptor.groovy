package wood.mike


class LoggingInterceptor {

    LoggingInterceptor() {
        matchAll()
    }

    boolean before() {
        log.info "Action invoked: URI=[${request.requestURL}] PARAMS=[${params}] METHOD=[${request.method}] for App ${grailsApplication.config.info.app.name}"
        true
    }
}
