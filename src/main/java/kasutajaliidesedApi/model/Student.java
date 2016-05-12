package kasutajaliidesedApi.model;

import java.util.List;

/**
 * Created by mart on 11.05.16.
 */
public class Student {

    private Long id;

    private String name;

    private List<Grade> grades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
