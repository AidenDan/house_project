package com.mapper;

import com.pojo.Users;
import com.pojo.UsersExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    //实现区域的批量删除
  /*  @Delete(
            "<script>delete FROM district  where id in\n" +
                    "     <foreach collection=\"array\" item=\"ids\" open=\"(\" close=\")\" separator=\",\">\n" +
                    "       #{ids}\n" +
                    "     </foreach></script>"
                   )*/
    Integer delUersByBatch(Integer[] ids);

    //房屋审核功能，把ispass设为1
    @Update("update house set ispass ='1' where id = #{houseId}")
    Integer makePass(@Param("houseId") String  houseId);

    //取消房屋审核通过，把ispass设为0
    @Update("update house set ispass ='0' where id = #{houseId}")
    Integer makeNoPass(@Param("houseId") String  houseId);

    //修改管理员密码
    @Update("update users set password=#{NewPass} where id =#{id}")
    Integer updateAdminPassword(@Param("id") String id, @Param("NewPass") String NewPass);
}





















