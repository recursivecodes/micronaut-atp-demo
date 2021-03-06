package codes.recursive.gorm.atp.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import grails.gorm.annotation.Entity
import groovy.transform.CompileStatic

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@CompileStatic
@Entity
class Person {
    @NotNull
    @Size(min=5, max=50)
    String firstName
    @Size(min=5, max=50)
    String lastName
    @NotNull
    Boolean isCool = false
    Date dateCreated
    Date lastUpdated
    Set<PersonHobby> personHobbies

    static hasMany = [personHobbies: PersonHobby]
    static graphql = true
}
