package com.woniu.yoga.user.util;

import com.woniu.yoga.user.dto.SearchConditionDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * @ClassName UserMapperProviderUtil
 * @Description TODO
 * @Author Administrator
 * @Date 2019/4/17 11:43
 * @Version 1.0
 **/
public class UserMapperProviderUtil {
    public String findAroundCoachs(SearchConditionDTO searchConditionDTO) {
        //联合user，coach表，条件1：userId相同；条件2：教练接受约教；条件3：经度纬度；条件2：只返回20条数据
        SQL sql = new SQL().SELECT(" real_name roleName,user.user_id userId,latitude,longitude,user_headimg headImg ").FROM(" coach,user ");
        //约束：角色为瑜伽师，或者场馆
        sql.WHERE(" coach.userId = user.userId and roleId = " + searchConditionDTO.getRoleId());
        //约束：姓名，模糊查询
        if (searchConditionDTO.getRealName() != null) {
          sql.WHERE(" real_name like '%"+searchConditionDTO.getRealName()+"' ");
        }
        if (searchConditionDTO.getRoleId() == 2) {
            //约束：接受约教且未满课的瑜伽师
            sql.WHERE(" coach.coachStatus = 1 and full_class = 0 ");
            //约束：认证方式
            if (searchConditionDTO.getAuthenticationMethod().equals("平台认证")) {
                sql.WHERE(" authentication = 2 ");
            }
            if (searchConditionDTO.getAuthenticationMethod().equals("场馆认证")) {
                sql.WHERE(" authentication = 1 ");
            }
            //约束：流派
            if (searchConditionDTO.getCoachStyle() != 0) {
                sql.WHERE(" coach_style = " + searchConditionDTO.getCoachStyle());
            }
            //约束：上班起始时间
            if (searchConditionDTO.getFreeTimeStart() != null) {
                sql.WHERE("start_time > '" + searchConditionDTO.getFreeTimeStart() + "' ");
            }
            //约束：上班完结时间
            if (searchConditionDTO.getFreeTimeEnd() != null) {
                sql.WHERE(" end_time < '" + searchConditionDTO.getFreeTimeEnd() + "' ");
            }
        }
        //约束：限定在一定区域
        sql.WHERE(" longitude <= " + searchConditionDTO.getWestLongitude() + " longitude >= " + searchConditionDTO.getEastLongitude());
        sql.WHERE(" latitude <= " + searchConditionDTO.getNothLatitude() + " latitude >= " + searchConditionDTO.getSouthLatitude());
        //约束：限定查询的数量
        String limit = sql.toString() + " limit 20";
        return limit;
    }
}
