package codes.recursive.gorm.atp.controller

import codes.recursive.gorm.atp.model.Person
import codes.recursive.gorm.atp.service.PersonService
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import org.grails.datastore.mapping.validation.ValidationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.validation.FieldError

import javax.annotation.Nullable
import javax.inject.Inject

@CompileStatic
@Controller("/person")
class PersonController {

    @Get("/")
    HttpStatus index() {
        return HttpStatus.OK
    }

    static final Logger LOG = LoggerFactory.getLogger(PersonController.class)

    @Inject PersonService personService

    @Get("/list{/offset}{/max}")
    List<Person> getPersons(@Nullable Optional<Integer> offset, @Nullable Optional<Integer> max) {
        if( offset && max ) {
            return personService.findAll([offset: offset.get(), max: max.get()])
        }
        else {
            return personService.findAll()
        }
    }


    @Get("/get/{id}")
    Person getPerson(int id) {
        return personService.find(id)
    }

    @Post("/save")
    HttpResponse<Map> savePerson(@Body Person person) {
        try {
            return HttpResponse.ok( [ person: personService.save(person) ] as Map )
        }
        catch(ValidationException e) {
            return HttpResponse.unprocessableEntity().body(
                    [
                            person: person,
                            errors: e.errors.allErrors.collect {
                                FieldError err = it as FieldError
                                [
                                        field: err.field,
                                        rejectedValue: err.rejectedValue,
                                        message: err.defaultMessage
                                ]
                            }
                    ]
            ) as HttpResponse<Map>
        }
    }

    @Put("/update")
    HttpResponse<Map> updatePerson(@Body Person person) {
        try {
            return HttpResponse.ok( [ person: personService.update(person.id, person.firstName, person.lastName) ] as Map )
        }
        catch(ValidationException e) {
            return HttpResponse.unprocessableEntity().body(
                    [
                            person: person,
                            errors: e.errors.allErrors.collect {
                                FieldError err = it as FieldError
                                [
                                        field: err.field,
                                        rejectedValue: err.rejectedValue,
                                        message: err.defaultMessage
                                ]
                            }
                    ]
            ) as HttpResponse<Map>
        }
    }

    @Delete("/delete/{id}")
    HttpResponse<Map> deletePerson(Long id) {
        try {
            return HttpResponse.ok( [person: personService.delete(id), deleted: true] as Map )
        }
        catch(e) {
            return HttpResponse.unprocessableEntity().body(
                    [
                            message: "Could not delete person with ID: ${id}"
                    ]
            ) as HttpResponse<Map>
        }
    }

}