package DAO

import entities.Patient
import org.hibernate.SessionFactory

class PatientDAO(
    private val sessionFactory: SessionFactory
) {
    fun save(patient: Patient) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.save(patient)
            session.transaction.commit()
        }
    }
    fun find(id: Long): Patient? {
        val result: Patient?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.get(Patient::class.java, id)
            session.transaction.commit()
        }
        return result
    }

    fun find(email: String): Patient? {
        val result: Patient?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result =
                session.byNaturalId(Patient::class.java).using("email", email).loadOptional().orElse(null)
            session.transaction.commit()
        }
        return result
    }

    fun findAll(): List<Patient> {
        val result: List<Patient>
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.createQuery("from Patient").list() as List<Patient>
            session.transaction.commit()
        }
        return result
    }

    fun delete(id: Long) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            val patient: Patient? = find(id)
            session.remove(patient)
            session.transaction.commit()
        }
    }


}
