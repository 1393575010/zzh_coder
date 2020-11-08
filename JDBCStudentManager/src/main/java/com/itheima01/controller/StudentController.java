package com.itheima01.controller;

import com.itheima01.domain.Student;
import com.itheima01.service.StudentService;
import com.itheima01.service.StudentServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class StudentController {
    private StudentService service = new StudentServiceImpl();

    @Test
    public void findAll() {
        ArrayList<Student> list = service.findAll();
        for (Student stu : list) {
            System.out.println(stu);
        }
    }

    @Test
    public void findById() {
        Student student = service.findbyId(3);
        System.out.println(student);

    }

    @Test
    public void insert() {
        Student stu = new Student(6, "海鲜", 30, new Date());
        int result = service.insert(stu);
        if (result != 0) System.out.println("添加成功");
        else System.out.println("添加失败");
    }

    @Test
    public void update() {
        Student stu = service.findbyId(5);
        stu.setName("哦几把");
        int result = service.update(stu);
        if (result != 0) System.out.println("修改成功");
        else System.out.println("修改失败");
    }

    @Test
    public void delete() {
        int result = service.delete(6);
        if (result != 0) System.out.println("删除成功");
        else System.out.println("删除失败");
    }

}
