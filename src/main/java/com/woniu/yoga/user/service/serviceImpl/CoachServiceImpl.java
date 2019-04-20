package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.user.dao.CoachMapper;
import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.service.CoachService;
import com.woniu.yoga.user.vo.Result;
import com.woniu.yoga.user.vo.StudentDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachMapper coachMapper;
    @Override
    public Coach findCoachByCoachId(Integer coachId) {
        Coach coach  = coachMapper.selectByPrimaryKey(coachId);
        return coach;
    }
    @Override
    public Result updateOrder(Integer orderId, String result) {
        //根据操作更改订单状态
        if (result.equals("接受")){

        }else if (result.equals("拒绝")){

        }else {
            //操作不合法
        }
        return null;
    }

    @Override
    public List<StudentDetailVO> listStudentByCoachId(Integer coachId) {
        //联合student_coach,coach,user查找学生信息
        return null;
    }

    @Override
    public List listCoachStyles() {
        return null;
    }

}
