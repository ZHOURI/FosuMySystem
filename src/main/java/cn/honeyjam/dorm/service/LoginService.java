package cn.honeyjam.dorm.service;

public interface LoginService {

    Object queryMember(String userId, String password, String sign);
}
