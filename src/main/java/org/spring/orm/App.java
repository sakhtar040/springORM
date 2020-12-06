package org.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import org.spring.orm.dao.StudentDao;
import org.spring.orm.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        System.out.println("Application Ready...");
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        App app = new App();
        System.out.println("***************** Welcome to spring orm project ******************");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while (!exit){
            app.options();
            try{
                int input = Integer.parseInt(bufferedReader.readLine());
                switch (input){
                    case 1:
                        app.insert(studentDao);
                        break;

                    case 2:
                        app.getSingle(studentDao);
                        break;

                    case 3:
                        app.getAll(studentDao);
                        break;

                    case 4:
                        app.update(studentDao);
                        break;

                    case 5:
                        app.delete(studentDao);
                        break;

                    case 6:
                        exit = true;
                        app.exit();
                        break;

                    default:
                        break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Invalid Input");
            }
        }
        System.out.println("Thankyou for using");
    }

    public void options(){
        System.out.println("Press 1: For Insert Student");
        System.out.println("Press 2: For Get Single Student");
        System.out.println("Press 3: For Get All Students");
        System.out.println("Press 4: For Update Student");
        System.out.println("Press 5: Delete Student");
        System.out.println("Press 6: For Exit!!");
        System.out.println("Enter one option:");
    }

    public void insert(StudentDao studentDao){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id:");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter student name:");
        String studentName = scanner.nextLine();

        System.out.println("Enter student address:");
        String studentAddress = scanner.nextLine();

        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName(studentName);
        student.setStudentAddress(studentAddress);

        int result = studentDao.insert(student);
        System.out.println("Inserted: "+result);
    }

    public void getSingle(StudentDao studentDao){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id:");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = studentDao.getStudent(studentId);
        System.out.println("Student Details: "+student.getStudentName() + " " + student.getStudentAddress());
    }

    public void getAll(StudentDao studentDao){
        List<Student> students = studentDao.getStudents();
        for (Student s: students){
            System.out.println("Student Details From All: "+s.getStudentName() + " " + s.getStudentAddress());
        }
    }

    public void update(StudentDao studentDao){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id:");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter student name:");
        String studentName = scanner.nextLine();

        System.out.println("Enter student address:");
        String studentAddress = scanner.nextLine();

        Student student = new Student(studentId, studentName, studentAddress);
        studentDao.updateStudent(student);
        System.out.println("Student Updated");
    }

    public void delete(StudentDao studentDao){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id:");
        int studentId = Integer.parseInt(scanner.nextLine());
        studentDao.deleteStudent(studentId);
        System.out.println("Student Deleted");
    }

    public void exit(){
        System.out.println("Exit");
    }
}