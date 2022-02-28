package wood.mike

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AnnouncementController {

    AnnouncementService announcementService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond announcementService.list(params), model:[announcementCount: announcementService.count()]
    }

    def show(Long id) {
        respond announcementService.get(id)
    }

    def create() {
        respond new Announcement(params)
    }

    def save(Announcement announcement) {
        if (announcement == null) {
            notFound()
            return
        }

        try {
            announcementService.save(announcement)
        } catch (ValidationException e) {
            respond announcement.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'announcement.label', default: 'Announcement'), announcement.id])
                redirect announcement
            }
            '*' { respond announcement, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond announcementService.get(id)
    }

    def update(Announcement announcement) {
        if (announcement == null) {
            notFound()
            return
        }

        try {
            announcementService.save(announcement)
        } catch (ValidationException e) {
            respond announcement.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'announcement.label', default: 'Announcement'), announcement.id])
                redirect announcement
            }
            '*'{ respond announcement, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        announcementService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'announcement.label', default: 'Announcement'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'announcement.label', default: 'Announcement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
