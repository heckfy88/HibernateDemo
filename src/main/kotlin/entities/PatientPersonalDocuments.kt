package entities

import javax.persistence.Column

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class PatientPersonalDocuments (

    @Id
    @GeneratedValue
    var id: Long = 0,

    var passport: String,
    var snils: String
)


