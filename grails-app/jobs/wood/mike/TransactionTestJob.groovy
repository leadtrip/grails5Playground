package wood.mike

/**
 * Testing the various propagation settings when called from a quartz job
 */
class TransactionTestJob {

    PropagationTestService propagationTestService

    static triggers = {
      simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {
        log.info "Propagation required result ${callPropagationTesService('propagationRequired')}"
        log.info "Propagation supports result ${callPropagationTesService('propagationSupports')}"
        log.info "Propagation mandatory result ${callPropagationTesService('propagationMandatory')}"
        log.info "Propagation requires new result ${callPropagationTesService('propagationRequiresNew')}"
        log.info "Propagation never result ${callPropagationTesService('propagationNever')}"
        log.info "Propagation nested result ${callPropagationTesService('propagationNested')}"
        log.info "Propagation not supported result ${callPropagationTesService('propagationNotSupported')}"
    }

    def callPropagationTesService(method) {
        def res
        try{
            res = propagationTestService."${method}"()
        }
        catch (all) {
            res = all.message
        }
        res
    }
}
