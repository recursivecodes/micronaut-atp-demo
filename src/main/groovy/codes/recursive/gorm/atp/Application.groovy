package codes.recursive.gorm.atp

import io.micronaut.runtime.Micronaut
import groovy.transform.CompileStatic

@CompileStatic
class Application {
    static void main(String[] args) {
        System.setProperty("oracle.jdbc.fanEnabled", "false")
        Micronaut.run(Application)
    }
}