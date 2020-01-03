package com.mapper;

import com.pojo.District;
import com.pojo.DistrictExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    //实现区域的批量删除
  /*  @Delete(
            "<script>delete FROM district  where id in\n" +
                    "     <foreach collection=\"array\" item=\"ids\" open=\"(\" close=\")\" separator=\",\">\n" +
                    "       #{ids}\n" +
                    "     </foreach></script>"
                   )*/
    Integer delDisrictByBatch(Integer[] ids);
}