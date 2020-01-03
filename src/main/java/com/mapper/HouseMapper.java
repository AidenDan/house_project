package com.mapper;

import com.condition.UsersCondition;
import com.pojo.House;
import com.pojo.HouseExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询当前用户的所有的房屋信息
    @Select("select house.ID, USER_ID, TITLE, DESCRIPTION, PRICE, PUBDATE, FLOORAGE, " +
             " CONTACT, PATH , ispass, isdel, street.NAME streetName, district.NAME districtName, type.NAME typeName " +
            " from house  " +
            " left join street on house.STREET_ID=street.ID" +
            " left join district on street.DISTRICT_ID=district.ID" +
            " left join type on house.TYPE_ID=type.ID" +
            " where USER_ID=#{userId} and isdel='0' order by PUBDATE desc")
    List<House> selectAllHouse(@Param("userId") Integer userId);

    //根据houseid修改房屋信息时，先查询当前房屋信息
    @Select("select house.ID, house.USER_ID userId, TITLE, DESCRIPTION, PRICE, PUBDATE, FLOORAGE, TYPE_ID typeId, STREET_ID streetId, street.DISTRICT_ID districtId," +
            " CONTACT, PATH , street.NAME streetName, district.NAME districtName, type.NAME typeName " +
            " from house  " +
            " left join street on house.STREET_ID=street.ID" +
            " left join district on street.DISTRICT_ID=district.ID" +
            " left join type on house.TYPE_ID=type.ID" +
            " where house.ID=#{houseId} order by PUBDATE desc ")
    House selectHouseByHouseId(@Param("houseId") String houseId);

    //下架房源
    @Update("update house set isdel  = '1' where id =#{houseId}")
    Integer downHouseByHouseId(@Param("houseId") String houseId);

    //查询当前用户的所有的房屋信息
    @Select("<script>select house.ID, house.USER_ID userId, TITLE, DESCRIPTION, PRICE, PUBDATE, FLOORAGE, ispass, isdel, TYPE_ID typeId, STREET_ID streetId, street.DISTRICT_ID districtId," +
            " CONTACT, PATH , street.NAME streetName, district.NAME districtName, type.NAME typeName " +
            " from house  " +
            " left join street on house.STREET_ID=street.ID" +
            " left join district on street.DISTRICT_ID=district.ID" +
            " left join type on house.TYPE_ID=type.ID" +
            " where 1 =1 " +
            " <if test=\"ctypeId!=null and ctypeId>0\">\n" +
            "        and TYPE_ID = #{ctypeId}\n" +
            "      </if>\n" +
            "      <if test=\"cdistrictId!=null and cdistrictId>0\">\n" +
            "        and street.DISTRICT_ID = #{cdistrictId}\n" +
            "      </if>\n" +
            "      <if test=\"cstreetId!=null and cstreetId>0\">\n" +
            "        and STREET_ID = #{cstreetId}\n" +
            "      </if>\n" +
            "      <if test=\"cispass!=null and cispass>=0\">\n" +
            "        and ispass = #{cispass}\n" +
            "      </if>\n" +
            "      <if test=\"cprice1!=null and cprice1 !='' \">\n" +
            "        and PRICE &gt;= #{cprice1}\n" +
            "      </if>\n" +
            "      <if test=\"cprice!=null and cprice !=''\">\n" +
            "        and PRICE &lt;= #{cprice}\n" +
            "      </if>\n" +
            "      <if test=\"ctitle!=null and ctitle !=''\">\n" +
            "        and TITLE like \"%\"#{ctitle}\"%\"" +
            "      </if>" +
            " order by PUBDATE desc</script>")
    List<House> selectAllHouse2(UsersCondition condition);

    //查询当前用户的所有的已审核的房屋信息
    @Select("<script>select house.ID, house.USER_ID userId, TITLE, DESCRIPTION, PRICE, PUBDATE, FLOORAGE, ispass, isdel, TYPE_ID typeId, STREET_ID streetId, street.DISTRICT_ID districtId," +
            " CONTACT, PATH , street.NAME streetName, district.NAME districtName, type.NAME typeName " +
            " from house  " +
            " left join street on house.STREET_ID=street.ID" +
            " left join district on street.DISTRICT_ID=district.ID" +
            " left join type on house.TYPE_ID=type.ID" +
            " where 1 =1 " +
            " <if test=\"ctypeId!=null and ctypeId>0\">\n" +
            "        and TYPE_ID = #{ctypeId}\n" +
            "      </if>\n" +
            "      <if test=\"cdistrictId!=null and cdistrictId>0\">\n" +
            "        and street.DISTRICT_ID = #{cdistrictId}\n" +
            "      </if>\n" +
            "      <if test=\"cstreetId!=null and cstreetId>0\">\n" +
            "        and STREET_ID = #{cstreetId}\n" +
            "      </if>\n" +
            "      <if test=\"cispass!=null and cispass>=0\">\n" +
            "        and ispass = #{cispass}\n" +
            "      </if>\n" +
            "      <if test=\"cprice1!=null and cprice1 !='' \">\n" +
            "        and PRICE &gt;= #{cprice1}\n" +
            "      </if>\n" +
            "      <if test=\"cprice!=null and cprice !=''\">\n" +
            "        and PRICE &lt;= #{cprice}\n" +
            "      </if>\n" +
            "      <if test=\"ctitle!=null and ctitle !=''\">\n" +
            "        and TITLE like \"%\"#{ctitle}\"%\"" +
            "      </if>" +
            " and ispass ='1' order by PUBDATE desc</script>")
    List<House> selectAllHouse3(UsersCondition condition);

    //查询当前用户的所有的未审核的房屋信息
    @Select("<script>select house.ID, house.USER_ID userId, TITLE, DESCRIPTION, PRICE, PUBDATE, FLOORAGE, ispass, isdel, TYPE_ID typeId, STREET_ID streetId, street.DISTRICT_ID districtId," +
            " CONTACT, PATH , street.NAME streetName, district.NAME districtName, type.NAME typeName " +
            " from house  " +
            " left join street on house.STREET_ID=street.ID" +
            " left join district on street.DISTRICT_ID=district.ID" +
            " left join type on house.TYPE_ID=type.ID" +
            " where 1 =1 " +
            " <if test=\"ctypeId!=null and ctypeId>0\">\n" +
            "        and TYPE_ID = #{ctypeId}\n" +
            "      </if>\n" +
            "      <if test=\"cdistrictId!=null and cdistrictId>0\">\n" +
            "        and street.DISTRICT_ID = #{cdistrictId}\n" +
            "      </if>\n" +
            "      <if test=\"cstreetId!=null and cstreetId>0\">\n" +
            "        and STREET_ID = #{cstreetId}\n" +
            "      </if>\n" +
            "      <if test=\"cispass!=null and cispass>=0\">\n" +
            "        and ispass = #{cispass}\n" +
            "      </if>\n" +
            "      <if test=\"cprice1!=null and cprice1 !='' \">\n" +
            "        and PRICE &gt;= #{cprice1}\n" +
            "      </if>\n" +
            "      <if test=\"cprice!=null and cprice !=''\">\n" +
            "        and PRICE &lt;= #{cprice}\n" +
            "      </if>\n" +
            "      <if test=\"ctitle!=null and ctitle !=''\">\n" +
            "        and TITLE like \"%\"#{ctitle}\"%\"" +
            "      </if>" +
            " and ispass ='0' order by PUBDATE desc</script>")
    List<House> selectAllHouse4(UsersCondition condition);

    /**
     *
     *
     * @description: 批量审核房屋
     * @param null
     * @param arr
     * @return:
     * @author: Aiden
     * @time: 2020-1-2 13:04:24
     */
    Integer verifyHouseDaoByBatch(String[] arr);


    //查询所有用户的所有的房屋信息
    @Select("<script>select house.ID, house.USER_ID userId, TITLE, DESCRIPTION, PRICE, PUBDATE, FLOORAGE, ispass, isdel, TYPE_ID typeId, STREET_ID streetId, street.DISTRICT_ID districtId," +
            " CONTACT, PATH , street.NAME streetName, district.NAME districtName, type.NAME typeName " +
            " from house  " +
            " left join street on house.STREET_ID=street.ID" +
            " left join district on street.DISTRICT_ID=district.ID" +
            " left join type on house.TYPE_ID=type.ID" +
            " where 1 =1 " +
            " <if test=\"ctypeId!=null and ctypeId>0\">\n" +
            "        and TYPE_ID = #{ctypeId}\n" +
            "      </if>\n" +
            "      <if test=\"cdistrictId!=null and cdistrictId>0\">\n" +
            "        and street.DISTRICT_ID = #{cdistrictId}\n" +
            "      </if>\n" +
            "      <if test=\"cstreetId!=null and cstreetId>0\">\n" +
            "        and STREET_ID = #{cstreetId}\n" +
            "      </if>\n" +
            "      <if test=\"cispass!=null and cispass>=0\">\n" +
            "        and ispass = #{cispass}\n" +
            "      </if>\n" +
            "      <if test=\"cprice1!=null and cprice1 !='' \">\n" +
            "        and PRICE &gt;= #{cprice1}" +
            "      </if>" +
            "      <if test=\"cprice!=null and cprice !=''\">\n" +
            "        and PRICE &lt;= #{cprice}" +
            "      </if>" +
            "      <if test=\"ctitle!=null and ctitle !=''\">" +
            "        and TITLE like \"%\"#{ctitle}\"%\"" +
            "      </if>" +
            "      <if test=\"cfloorage1!=null and cfloorage1 !='' \">" +
            "                    and floorage &gt;= #{cfloorage1}" +
            "                 </if>" +
            "                  <if test=\"cfloorage!=null and cfloorage !=''\"> " +
            "                   and PRICE &lt;= #{cfloorage} " +
            "                         </if> " +
            " and ispass =1 order by PUBDATE desc</script>")
    List<House> selectAllHouse5(UsersCondition condition);
}



















