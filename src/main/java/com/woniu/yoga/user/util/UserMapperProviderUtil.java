package com.woniu.yoga.user.util;

import com.woniu.yoga.user.dto.SearchConditionDTO;
import com.woniu.yoga.user.vo.SearchConditionVO;
import org.apache.ibatis.jdbc.SQL;

/**
 * @ClassName UserMapperProviderUtil
 * @Description 为UserMapper提供sql开发
 * @Author Administrator
 * @Date 2019/4/17 11:43
 * @Version 1.0
 **/
public class UserMapperProviderUtil {
    public String findAroundCoachs(SearchConditionDTO searchConditionDTO) {
        //联合user，coach表，条件1：userId相同；条件2：教练接受约教；条件3：经度纬度；条件2：只返回20条数据
        SQL sql = new SQL().SELECT(" real_name realName,user.user_id userId,latitude,longitude,user_headimg headImg,good_comment goodComment,bad_comment badComment,common_comment commonComment,dict_item_name coachStyle ").FROM(" coach,user,base_dict ");
        //约束：用户ID相等，且角色为瑜伽
        sql.WHERE(" coach.user_id = user.user_id  and role_id =2");
        //约束：姓名，模糊查询
        if (searchConditionDTO.getRealName() != null) {
            sql.WHERE(" real_name like '%" + searchConditionDTO.getRealName() + "%' ");
        }
        //约束：流派
        if (searchConditionDTO.getCoachStyle() != 0) {
            sql.WHERE("and dict_id = " + searchConditionDTO.getCoachStyle() + " and dict_type_code = 1");
        }
        //约束：接受约教且未满课的瑜伽师
        sql.WHERE(" coach.coach_status = 1 and full_class = 0 ");
        //约束：认证方式
        if (searchConditionDTO.getAuthentication() != null) {
            sql.WHERE(" authentication = " + searchConditionDTO.getAuthentication());
        }
        //约束：上班起始时间
        if (searchConditionDTO.getFreeTimeStart() != null) {
            sql.WHERE("start_time > '" + searchConditionDTO.getFreeTimeStart() + "' ");
        }
        //约束：上班完结时间
        if (searchConditionDTO.getFreeTimeEnd() != null) {
            sql.WHERE(" end_time < '" + searchConditionDTO.getFreeTimeEnd() + "' ");
        }
        //约束：限定在一定区域
        sql.WHERE(" longitude <= " + searchConditionDTO.getWestLongitude() + "and longitude >= " + searchConditionDTO.getEastLongitude());
        sql.WHERE(" latitude <= " + searchConditionDTO.getNothLatitude() + "and latitude >= " + searchConditionDTO.getSouthLatitude());
        sql.WHERE(" user_flag = 0 ");
        //约束：限定查询的数量
        String limit = sql.toString() + " limit 20";
        return limit;
    }

    public String listAroundVenues(SearchConditionDTO searchConditionDTO) {
        SQL sql = new SQL().SELECT(" user.user_id userId,real_name realName,longitude,latitude,user_headimg headImg,clicks ").FROM(" user,venue ");
        sql.WHERE(" user.user_id = venue.user_id and role_id = 3 ");
        if (searchConditionDTO.getRealName() != null) {
            sql.WHERE(" real_name like '%" + searchConditionDTO.getRealName() + "%' ");
        }
        sql.WHERE(" longitude <= " + searchConditionDTO.getWestLongitude() + "and longitude >= " + searchConditionDTO.getEastLongitude());
        sql.WHERE(" latitude <= " + searchConditionDTO.getNothLatitude() + "and latitude >= " + searchConditionDTO.getSouthLatitude());
        sql.WHERE(" user_flag = 0 ");
        //约束：限定查询的数量
        String limit = sql.toString() + " limit 20";
        return limit;
    }
}
