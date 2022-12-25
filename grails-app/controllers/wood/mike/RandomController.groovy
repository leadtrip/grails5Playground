package wood.mike

class RandomController {

    def randomOneService

    def index() {
        try{
            randomOneService.execute()
            render 'executed'
        }
        catch (CustomException ce) {
            log.error(ce.message)
            render "failed: ${ce.message}"
        }
    }
}
