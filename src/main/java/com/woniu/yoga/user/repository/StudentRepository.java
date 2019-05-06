package com.woniu.yoga.user.repository;

import com.woniu.yoga.user.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述StudentRepository
 * @Author: lxy
 * @time: 2019/4/25 15:40
 */
@Transactional
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findStudentByUserId(Integer userId);
    Student save(Student student);
}
