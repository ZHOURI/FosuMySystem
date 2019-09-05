package cn.honeyjam.dorm.mapper;

import cn.honeyjam.dorm.pojo.Health;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface HealthMapper extends Mapper<Health> {
}