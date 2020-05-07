package codes.recursive.gorm.atp.service

import codes.recursive.gorm.atp.model.Hobby
import codes.recursive.gorm.atp.model.Person
import codes.recursive.gorm.atp.model.PersonHobby
import codes.recursive.gorm.atp.model.dto.PersonDTO
import grails.gorm.services.Join
import grails.gorm.services.Query
import grails.gorm.services.Service
import groovy.transform.CompileStatic

import javax.transaction.Transactional
import javax.validation.constraints.NotNull

@CompileStatic
@Service(Person)
@Transactional
abstract class PersonService {
    abstract int count()
    @Join("personHobbies")
    abstract List<Person> findAll()
    @Join("personHobbies")
    abstract List<Person> findAll(Map args)
    abstract Person find(@NotNull Long id)
    abstract Person findByFirstName(@NotNull String firstName)
    abstract Person save(Person person)
    abstract Person update(@NotNull Long id, @NotNull String firstName, @NotNull String lastName)
    abstract Person delete(@NotNull Long id)

    List<PersonDTO> findAllDtos(Map args=[:]) {
        List<Person> persons = findAll(args)
        List<PersonDTO> dtos = []

        persons.each { Person it ->
            Set<Hobby> hobbies = it.personHobbies.collect { PersonHobby ph -> ph.hobby } as Set<Hobby>
            dtos << new PersonDTO(
                    firstName: it.firstName,
                    lastName: it.lastName,
                    isCool: it.isCool,
                    hobbies:  hobbies,
            )
        }
        return dtos
    }
}