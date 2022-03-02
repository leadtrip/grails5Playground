package wood.mike

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import org.springframework.http.HttpStatus


/**
 * This class demonstrates usage of command objects.
 *
 * A command object is a class that is used in conjunction with data binding, usually to allow
 * validation of data that may not fit into an existing domain class.
 * A class is only considered to be a command object when it is used as a parameter of an action.
 * While a domain class can be used as a command object, so also can non-domain classes defined in other files
 * (or even classes defined in the same file as the controller using the class, see below).
 */
@ReadOnly
class PlayerController {

    def index() {
        respond Player.list(params), model: [playerCount: Player.count()]
    }

    def show(Player player) {
        respond player
    }

    /**
     * Player here is not a command object as it's not an argument to the action
     * @return
     */
    @SuppressWarnings(['FactoryMethodName', 'GrailsMassAssignment'])
    def create() {
        respond new Player(params)
    }

    @Transactional
    def save(Player player) {
        if (player == null) {
            render status: HttpStatus.NOT_FOUND
            return
        }

        if (player.hasErrors()) {
            respond player.errors, view: 'create'
            return
        }

        player.save flush: true

        request.withFormat {
            form multipartForm { redirect player }
            '*' { respond player, status: HttpStatus.CREATED }
        }
    }

    def edit(Player player) {
        respond player
    }

    @Transactional
    def update(PlayerInfo info) {
        if (info.hasErrors()) {
            respond info.errors, view: 'edit'
            return
        }
        Player player = Player.get(params.id)
        if (player == null) {
            render status: HttpStatus.NOT_FOUND
            return
        }

        player.properties = info.properties
        player.save flush: true

        request.withFormat {
            form multipartForm { redirect player }
            '*' { respond player, status: HttpStatus.OK }
        }
    }
}

/**
 * If your command object class is defined in the same source file as the controller using it,
 * Grails will automatically make it Validateable, permitting the use of a static constraints block.
 * If you define the class elsewhere and need constraints, you need to implement grails.validation.Validateable
 *
 * Using this command object instead of the Player domain object allows us to limit what fields can be updated for
 * certain actions such as update above where we don't want the wins and losses fields updated
 */
class PlayerInfo {
    String name
    String game
    String region

    static constraints = {
        name blank: false
        game blank: false
        region nullable: true
    }
}