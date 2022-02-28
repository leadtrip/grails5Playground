package wood.mike

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AnnouncementServiceSpec extends Specification {

    AnnouncementService announcementService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Announcement(...).save(flush: true, failOnError: true)
        //new Announcement(...).save(flush: true, failOnError: true)
        //Announcement announcement = new Announcement(...).save(flush: true, failOnError: true)
        //new Announcement(...).save(flush: true, failOnError: true)
        //new Announcement(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //announcement.id
    }

    void "test get"() {
        setupData()

        expect:
        announcementService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Announcement> announcementList = announcementService.list(max: 2, offset: 2)

        then:
        announcementList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        announcementService.count() == 5
    }

    void "test delete"() {
        Long announcementId = setupData()

        expect:
        announcementService.count() == 5

        when:
        announcementService.delete(announcementId)
        sessionFactory.currentSession.flush()

        then:
        announcementService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Announcement announcement = new Announcement()
        announcementService.save(announcement)

        then:
        announcement.id != null
    }
}
