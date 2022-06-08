package wood.mike

class ExportController {

    def exportService

    def index() {}

    def exportBooks() {
        exportService.export('csv', response.outputStream, Book.all, null, null )
    }

    def exportPlayers() {
        def fields = ['name', 'game']
        def labels = ['name':'thename', 'game': 'thegame']
        exportService.export('csv', response.outputStream, Player.all, fields, labels, null, null )
    }
}
