package cn.honeyjam.dorm.service.impl;

import cn.honeyjam.dorm.mapper.ManagerMapper;
import cn.honeyjam.dorm.mapper.StudentMapper;
import cn.honeyjam.dorm.pojo.Manager;
import cn.honeyjam.dorm.pojo.Student;
import cn.honeyjam.dorm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ManagerMapper managerMapper;
    public Object queryMember(String userId1, String password, String sign) {
        Long userId = Long.parseLong(userId1);
        if(sign.equals("学生"))
        {
            Student student1 = studentMapper.queryStudent(userId,password);
            if(student1!=null)
            {
                student1.setSign("学生");
                return student1;
            }
        }
        else if(sign.equals("宿管"))
        {
            Manager manager = new Manager();
            manager.setId(userId);
            manager.setPassword(password);
            Manager manager1 = managerMapper.selectOne(manager);
            if(manager1!=null)
            {
                manager1.setSign("宿管");
                return manager1;
            }
        }
        return null;
    }
}
