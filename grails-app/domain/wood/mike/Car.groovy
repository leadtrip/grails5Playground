package wood.mike

class Car {

    String model
    Double engineSize
    Date releaseDate
    static belongsTo = [manufacturer: CarManufacturer]

}
