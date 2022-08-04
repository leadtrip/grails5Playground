package wood.mike

class ChickenController {

    def chickenService
    def throwExceptionService

    def index() {
        render "Standing"
    }

    def cluck() {
        render "Clucking"
    }

    def walk() {
        render "Walking"
    }

    def stuff() {}

    def create() {
        render (view: 'stuff', model: [result: chickenService.create()] )
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

    def getStuff() {
        render view: "stuff", model: [standardThing: 'clouds'] << getExtraStuff()
    }

    protected Map getExtraStuff() {
        return [extraItem1: 'bread', extraItem2: 'cheese']
    }

    def saveChickenCatchCheckedException() {
        chickenService.create()
        try {
            throwExceptionService.throwChecked()
        }
        catch(Exception e) {
            log.error('Exception caught ' + e.getMessage())
        }
        redirect(action: 'list')
    }

    def saveChickenCatchUncheckedException() {
        chickenService.create()
        try {
            throwExceptionService.throwUnchecked()
        }
        catch(Exception e) {
            log.error('Exception caught '  + e.getMessage())
        }
        redirect(action: 'list')
    }

    def list() {
        respond chickenService.all()
    }
}
