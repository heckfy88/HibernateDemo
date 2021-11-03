import DAO.PatientDAO
import entities.OutpatientDepartment
import entities.Patient
import entities.PatientPersonalDocuments
import org.hibernate.cfg.Configuration
import java.time.LocalDate

fun main() {
    val sessionFactory = Configuration().configure()
        .addAnnotatedClass(Patient::class.java)
        .addAnnotatedClass(OutpatientDepartment::class.java)
        .addAnnotatedClass(PatientPersonalDocuments::class.java)
        .buildSessionFactory()

    sessionFactory.use { sessionFactory ->
        val dao = PatientDAO(sessionFactory)



        val patient1: Patient = Patient(
            name = "Джо Джо Джамбалайа",
            email = "joejoe@joejoe.joejoe",
            birthDate = LocalDate.of(1999, 10, 10),
            personalDocuments = PatientPersonalDocuments(
                passport = "0000 000000",
                snils = "777-777-777 53"
            ),
            outpatientDepartment = mutableListOf(
                OutpatientDepartment(
                    street = "1st st."
                ),
                OutpatientDepartment(
                    street = "10st st."
                ),
                OutpatientDepartment(
                    street = "100st st."
                ),
            )
        )
        val patient2: Patient = Patient(
            name = "Джо2 Джо2 Джамбалайа2",
            email = "joe2joe2@joejoe.joejoe",
            birthDate = LocalDate.of(19992, 12, 12),
            personalDocuments = PatientPersonalDocuments(
                passport = "2222 222222",
                snils = "222-222-222 53"
            ),
            outpatientDepartment = mutableListOf(
                OutpatientDepartment(
                    street = "2st st."
                ),
                OutpatientDepartment(
                    street = "20st st."
                ),
                OutpatientDepartment(
                    street = "200st st."
                ),
            )
        )


        dao.save(patient1)
        dao.save(patient2)

        val found = dao.find(patient1.id)
        println("Найден пациент: $found \n")

        val found2 = dao.find(patient2.id)
        println("Найден пациент: $found2 \n")

    }
}
