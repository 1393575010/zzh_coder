package com.itheima01.service;

import com.itheima01.dao.StudentDao;
import com.itheima01.dao.StudentDaoImpl;
import com.itheima01.domain.Student;

import java.util.ArrayList;

public class StudentServiceImpl implements StudentService {
    private StudentDao dao = new StudentDaoImpl();

    @Override
    public ArrayList<Student> findAll() {
        return dao.findAll();
    }

    @Override
    public Student findbyId(Integer id) {
        return dao.findbyId(id);
    }

    @Override
    public int insert(Student stu) {
        return dao.insert(stu);
    }

    @Override
    public int update(Student stu) {
        return dao.update(stu);
    }

    @Override
    public int delete(Integer id) {
        return dao.delete(id);
    }
}
