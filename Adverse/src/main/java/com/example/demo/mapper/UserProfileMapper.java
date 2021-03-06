package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.UserProfile;

@Mapper
public interface UserProfileMapper {
	
	@Select("SELECT * FROM UserProfile WHERE id =#{id}")
	UserProfile getUserProfile(@PathVariable("id") String id);
	
	@Select("SELECT count(*) FROM MEMBER_INFO WHERE id =#{id}")
	int getAvailableId(String id);
	
	@Select("SELECT * FROM UserProfile")
	List<UserProfile> getUserProfileList();
	
	@Insert("INSERT INTO MEMBER_INFO VALUES(get_seq('MemberSeq'),#{id},#{name},#{password})")
	int registerUserProfile(String id, String name, String password);
	
	@Select("SELECT COUNT(*) FROM MEMBER_INFO WHERE id = #{id} AND password = #{password}")
	int getUserInfo(String id, String password);

	@Insert("INSERT INTO UserProfile VALUES(#{id},#{name},#{phone},#{address})")
	int insertUserProfile(@Param("id")String id, @Param("name")String name, @Param("phone")String phone,@Param("address")String address);

	@Update("UPDATE UserProfile SET name=#{name}, phone=#{phone},address=#{address} WHERE id =#{id}")
	int updateUserProfile(@Param("id")String id, @Param("name")String name, @Param("phone")String phone,@Param("address")String address);

	@Delete("DELETE FROM UserProfile WHERE id =#{id}")
	int deleteUserProfile(@Param("id") String id);
	
}
