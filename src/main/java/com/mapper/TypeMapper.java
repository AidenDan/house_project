package com.mapper;

import com.pojo.Type;
import com.pojo.TypeExample;
import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    //实现区域的批量删除
  /*  @Delete(
            "<script>delete FROM district  where id in\n" +
                    "     <foreach collection=\"array\" item=\"ids\" open=\"(\" close=\")\" separator=\",\">\n" +
                    "       #{ids}\n" +
                    "     </foreach></script>"
                   )*/
    Integer delTypeByBatch(Integer[] ids);
}