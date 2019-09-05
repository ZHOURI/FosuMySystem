package cn.honeyjam.dorm.service;

import cn.honeyjam.dorm.pojo.*;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Student queryOwnInfo(Long userId);

    Boolean updatePassword(String password,Long userId);

    Room queryRoomInfo(String roomId);

    boolean saveStayForm(Stay stay);



    boolean saveRepairForm(Student student,Repair repair);

    List<Notice> queryNotice();

    PageResult<Stay> queryStay(Long id, Integer currentPage, Integer rows, Map<String, String[]> map);

    PageResult<Fees> queryFees(Long id, Integer currentPage, Integer rows, Map<String, String[]> map);

    PageResult<Repair> queryRepair(Repair repair, Integer currentPage, Integer rows, Map<String, String[]> map);

    Repair queryRepairOne(Integer id);
}
