package wood.mike

class Address {

    String street
    String city

    static constraints = {
        street maxSize: 75, nullable: true, blank: false
        city maxSize: 50, nullable: true, blank: true
    }
}
