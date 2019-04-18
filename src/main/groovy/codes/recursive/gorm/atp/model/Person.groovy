package codes.recursive.gorm.atp.model

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
}
