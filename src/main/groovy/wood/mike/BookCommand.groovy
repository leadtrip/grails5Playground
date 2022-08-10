package wood.mike

import grails.validation.Validateable

class BookCommand implements Validateable{

    String image
    String title
    String author
    String about
    String href

    static constraints = {
        title(validator: {val, cmd ->
            if( cmd.title ) {
                def allBooks = Book.all
                println "I got all the books $allBooks"
                def chicken = new Chicken(name: 'Albert', address: new Address(city: 'London', street: 'Bank street')).save()
                println "Saved new chicken with id ${chicken.id}"
                true
            }
        })
    }
}
