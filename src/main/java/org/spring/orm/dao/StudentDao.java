package org.spring.orm.dao;

import java.util.List;
import org.spring.orm.entities.Student;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public class StudentDao {
    private HibernateTemplate hibernateTemplate;

    //save student
    @Transactional
    public int insert(Student student){
        Integer result = (Integer) this.hibernateTemplate.save(student);
        return result;
    }

    //get Single Student
    public Student getStudent(int studentId){
        Student student = this.hibernateTemplate.get(Student.class, studentId);
        return student;
    }

    //get All Students
    public List<Student> getStudents(){
        List<Student> students = this.hibernateTemplate.loadAll(Student.class);
        return students;
    }

    //Update Student
    @Transactional
    public void updateStudent(Student student){
        this.hibernateTemplate.update(student);
    }

    //Delete Student
    @Transactional
    public void deleteStudent(int studentId){
        Student student = this.hibernateTemplate.get(Student.class, studentId);
        this.hibernateTemplate.delete(student);
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
