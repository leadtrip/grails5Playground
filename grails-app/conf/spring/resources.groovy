import wood.mike.Dog
import wood.mike.MikeBeanImpl

// Place your Spring DSL code here
beans = {
    mikeBean(MikeBeanImpl) {        // accessed in MikeController
        volume = 9382
        name = 'Vlad'
        chickenService = ref('chickenService')      // reference an existing bean
        dog = ref('fido')
    }

    fido(Dog) {
        name = 'Fido'
        age = 3
    }
}
