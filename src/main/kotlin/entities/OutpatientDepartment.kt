package entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class OutpatientDepartment(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var street: String
)