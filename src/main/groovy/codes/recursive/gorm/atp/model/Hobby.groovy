package codes.recursive.gorm.atp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import grails.gorm.annotation.Entity
import groovy.transform.CompileStatic

import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

@CompileStatic
@Entity
class Hobby {
    @NotNull
    @Size(max=250)
    String name
    @NotNull
    @PositiveOrZero
    BigDecimal monthlyCost=0.00
    Date dateCreated
    Date lastUpdated
    @JsonIgnore
    PersonHobby personHobby

    static graphql = true
    static belongsTo = [personHobby: PersonHobby]
}
