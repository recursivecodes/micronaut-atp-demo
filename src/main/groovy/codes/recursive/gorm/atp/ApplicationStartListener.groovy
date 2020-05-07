package codes.recursive.gorm.atp

import codes.recursive.gorm.atp.model.Hobby
import codes.recursive.gorm.atp.model.Person
import codes.recursive.gorm.atp.model.PersonHobby
import codes.recursive.gorm.atp.service.PersonService
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceReadyEvent

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApplicationStartListener implements ApplicationEventListener<ServiceReadyEvent> {

    @Inject PersonService personService

    @Override
    void onApplicationEvent(ServiceReadyEvent event) {

        Person demoUser = personService.findByFirstName('Toddrick')

        if( !demoUser ) {
            demoUser = new Person(firstName: 'Toddrick', lastName: 'Sharplenz', isCool: true)
            def ph1 = new PersonHobby(person: demoUser, hobby: new Hobby(name: 'Coding') )
            def ph2 = new PersonHobby(person: demoUser, hobby: new Hobby(name: 'Video Games', monthlyCost: 50.00) )
            demoUser.addToPersonHobbies(ph1)
            demoUser.addToPersonHobbies(ph2)
            personService.save(demoUser)
        }

    }

}

