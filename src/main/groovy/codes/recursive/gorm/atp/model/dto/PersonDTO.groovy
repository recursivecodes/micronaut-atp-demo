package codes.recursive.gorm.atp.model.dto

import codes.recursive.gorm.atp.model.Hobby

class PersonDTO {
    String firstName
    String lastName
    Boolean isCool = false
    Date dateCreated
    Date lastUpdated
    Set<Hobby> hobbies
}
