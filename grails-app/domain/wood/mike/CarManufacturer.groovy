package wood.mike

class CarManufacturer {

    String name
    Date established
    static hasMany = [cars: Car]
}
