package com.mapper;

import com.pojo.Street;
import com.pojo.StreetExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    //删除区域的时候要把对应的街道给删除掉
    @Delete("DELETE from street where DISTRICT_ID = #{id}")
    Integer delStreetByDistrict_Id(@Param("id") Integer id);

    //实现区域的批量删除
  /*  @Delete(
            "<script>delete FROM district  where id in\n" +
                    "     <foreach collection=\"array\" item=\"ids\" open=\"(\" close=\")\" separator=\",\">\n" +
                    "       #{ids}\n" +
                    "     </foreach></script>"
                   )*/
    Integer delStreetByBatch(Integer[] ids);
}