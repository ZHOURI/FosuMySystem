package cn.honeyjam.dorm.mapper;

import cn.honeyjam.dorm.pojo.Student;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface StudentMapper extends Mapper<Student> {
    @Select("select * from tb_stu where id = #{userId} and password=#{password}")
    public Student queryStudent(@Param("userId") Long userId,@Param("password") String password);
}