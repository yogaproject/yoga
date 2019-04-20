package com.woniu.yoga.user.util;

import com.woniu.yoga.user.dto.SearchConditionDTO;
import com.woniu.yoga.user.vo.SearchConditionVO;

/**
 * @Author liufeng
 * @ClassName ConvertVOToDTOUtil
 * @Date 2019/4/19 10:13
 * @Version 1.0
 * @Description TODO
 **/
public class ConvertVOToDTOUtil {
    public static SearchConditionDTO searchConditionConvert(double[] bounds, SearchConditionVO searchConditionVO) {
        SearchConditionDTO searchConditionDTO = new SearchConditionDTO();
        searchConditionDTO.setAuthenticationMethod(searchConditionVO.getAuthenticationMethod());
        searchConditionDTO.setCoachStyle(searchConditionVO.getCoachStyle());
        if (searchConditionVO.getFreeTime().equals("不限")) {
            searchConditionDTO.setFreeTimeStart(null);
            searchConditionDTO.setFreeTimeEnd(null);
        }
        if (searchConditionVO.getFreeTime().equals("早")) {
            searchConditionDTO.setFreeTimeStart("9:00:00");
            searchConditionDTO.setFreeTimeEnd(null);
        }
        if (searchConditionVO.getFreeTime().equals("中")) {
            searchConditionDTO.setFreeTimeStart(null);
            searchConditionDTO.setFreeTimeEnd(null);
        }
        if (searchConditionVO.getFreeTime().equals("晚")) {
            searchConditionDTO.setFreeTimeStart(null);
            searchConditionDTO.setFreeTimeEnd(null);
        }
        if (searchConditionVO.getFreeTime().equals("全天")) {
            searchConditionDTO.setFreeTimeStart(null);
            searchConditionDTO.setFreeTimeEnd(null);
        }
        searchConditionDTO.setRoleId(searchConditionVO.getRoleId());
        searchConditionDTO.setEastLongitude(bounds[0]);
        searchConditionDTO.setWestLongitude(bounds[1]);
        searchConditionDTO.setSouthLatitude(bounds[2]);
        searchConditionDTO.setNothLatitude(bounds[3]);
        searchConditionDTO.setRealName(searchConditionVO.getRealName());
        return searchConditionDTO;
    }
}
