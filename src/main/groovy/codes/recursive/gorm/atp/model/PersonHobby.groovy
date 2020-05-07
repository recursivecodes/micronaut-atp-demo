package codes.recursive.gorm.atp.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import grails.gorm.annotation.Entity

@Entity
class PersonHobby {
    @JsonIgnore
    Person person
    Hobby hobby
    Date dateCreated
    Date lastUpdated

    static mapping = {
        hobby fetch: 'join'
    }
}
