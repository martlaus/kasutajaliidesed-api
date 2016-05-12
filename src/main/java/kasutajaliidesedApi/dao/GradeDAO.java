package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.model.Grade;
import kasutajaliidesedApi.model.Homework;
import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mart on 11.05.16.
 */
public class GradeDAO {

    @Inject
    private EntityManager entityManager;


    public List<Grade> getGradeByStudentAndSubject(User student, long subject) {
        TypedQuery<Grade> findByCode = entityManager
                .createQuery("SELECT u FROM Grade u WHERE u.user = :student AND u.homework.subject.id = :subject ORDER BY u.homework.name"
                        , Grade.class);

        List<Grade> grade = null;
        try {
            grade = findByCode.setParameter("student", student).setParameter("subject", subject).getResultList();
        } catch (NoResultException ex) {
            // ignore
        }

        return grade;
    }

    public Grade getGradeById(long id) {
        TypedQuery<Grade> findByCode = entityManager
                .createQuery("SELECT u FROM Grade u WHERE u.id = :id"
                        , Grade.class);

        Grade grade = null;
        try {
            grade = findByCode.setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            // ignore
        }

        return grade;
    }

    public Grade update(Grade grade) {

        Grade merged;
        try {
            merged = entityManager.merge(grade);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            throw new RuntimeException("Exception when persisting joke.");
        }

        return merged;
    }

}
