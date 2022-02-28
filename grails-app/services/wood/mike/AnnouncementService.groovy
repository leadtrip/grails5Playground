package wood.mike

import grails.gorm.services.Service

@Service(Announcement)
interface AnnouncementService {

    Announcement get(Serializable id)

    List<Announcement> list(Map args)

    Long count()

    void delete(Serializable id)

    Announcement save(Announcement announcement)

}