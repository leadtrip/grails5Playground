package wood.mike

import grails.gorm.transactions.Transactional

class PropagationTestController {

    def propagationTestService

    def index() { }

    def propagationNotSupported() {
        redirect( action: 'index', params: propagationTestService.propagationNotSupported() )
    }

    @Transactional
    def propagationNotSupportedControllerTransactional() {
        redirect( action: 'index', params: propagationTestService.propagationNotSupported() )
    }

    def propagationRequired() {
        redirect(action: 'index', params: propagationTestService.propagationRequired() )
    }

    @Transactional
    def propagationRequiredControllerTransactional() {
        redirect(action: 'index', params: propagationTestService.propagationRequired() )
    }

    def propagationSupports() {
        redirect(action: 'index', params: propagationTestService.propagationSupports() )
    }

    @Transactional
    def propagationSupportsControllerTransactional() {
        redirect(action: 'index', params: propagationTestService.propagationSupports() )
    }

    def propagationMandatory() {
        redirect(action: 'index', params: propagationTestService.propagationMandatory() )
    }

    @Transactional
    def propagationMandatoryControllerTransactional() {
        redirect(action: 'index', params: propagationTestService.propagationMandatory() )
    }

    def propagationRequiresNew() {
        redirect(action: 'index', params: propagationTestService.propagationRequiresNew() )
    }

    @Transactional
    def propagationRequiresNewControllerTransactional() {
        redirect(action: 'index', params: propagationTestService.propagationRequiresNew() )
    }

    def propagationNever() {
        redirect(action: 'index', params: propagationTestService.propagationNever() )
    }

    @Transactional
    def propagationNeverControllerTransactional() {
        redirect(action: 'index', params: propagationTestService.propagationNever() )
    }

    def propagationNested() {
        redirect(action: 'index', params: propagationTestService.propagationNested() )
    }

    @Transactional
    def propagationNestedControllerTransactional() {
        redirect(action: 'index', params: propagationTestService.propagationNested() )
    }
}
