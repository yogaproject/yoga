package com.woniu.yoga.user.util;

import com.woniu.yoga.user.dto.CoachDTO;
import com.woniu.yoga.user.dto.SearchConditionDTO;
import com.woniu.yoga.user.vo.CoachVO;
import com.woniu.yoga.user.vo.SearchConditionVO;

import java.util.ArrayList;
import java.util.List;

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
        if (searchConditionVO.getAuthentication().equals("不限")) {
            searchConditionDTO.setAuthentication(null);
        } else if (searchConditionVO.getAuthentication().equals("平台认证")) {
            searchConditionDTO.setAuthentication(2);
        } else {
            searchConditionDTO.setAuthentication(1);
        }
        searchConditionDTO.setCoachStyle(searchConditionVO.getCoachStyle());
        if (searchConditionVO.getFreeTime().equals("不限")) {
            searchConditionDTO.setFreeTimeStart(null);
            searchConditionDTO.setFreeTimeEnd(null);
        }
        if (searchConditionVO.getFreeTime().equals("早")) {
            searchConditionDTO.setFreeTimeStart("8:00:00");
            searchConditionDTO.setFreeTimeEnd("12:00:00");
        }
        if (searchConditionVO.getFreeTime().equals("中")) {
            searchConditionDTO.setFreeTimeStart("11:00:00");
            searchConditionDTO.setFreeTimeEnd("18:00:00");
        }
        if (searchConditionVO.getFreeTime().equals("晚")) {
            searchConditionDTO.setFreeTimeStart("17:00:00");
            searchConditionDTO.setFreeTimeEnd("22:00:00");
        }
        if (searchConditionVO.getFreeTime().equals("全天")) {
            searchConditionDTO.setFreeTimeStart("8:00:00");
            searchConditionDTO.setFreeTimeEnd("22:00:00");
        }
        searchConditionDTO.setEastLongitude(bounds[0]);
        searchConditionDTO.setWestLongitude(bounds[1]);
        searchConditionDTO.setSouthLatitude(bounds[2]);
        searchConditionDTO.setNothLatitude(bounds[3]);
        searchConditionDTO.setRealName(searchConditionVO.getRealName());
        return searchConditionDTO;
    }

    public static List<CoachVO> convertCoachDTOtoVO(List<CoachDTO> listAroundCoach) {
        //计算好评率
        List<CoachVO> coachVOS = new ArrayList<>(32);
        for (int i = 0; i < listAroundCoach.size(); i++) {
            CoachDTO coachDTO = listAroundCoach.get(i);
            CoachVO coachVO = new CoachVO();
            coachVO.setCoachStyle(coachDTO.getCoachStyle());
            coachVO.setHeadImg(coachDTO.getHeadImg());
            coachVO.setLatitude(coachDTO.getLatitude());
            coachVO.setLongitude(coachDTO.getLongitude());
            coachVO.setRealName(coachDTO.getRealName());
            coachVO.setUserId(coachDTO.getUserId());
            int commentCount = coachDTO.getBadComment() + coachDTO.getCommonComment() + coachDTO.getGoodComment();
            float feedback = (coachDTO.getBadComment() * 1 + coachDTO.getCommonComment() * 3 + coachDTO.getGoodComment() * 5) / (float)commentCount;
//            (float)(Math.round(totalPrice*100)/100)
            feedback = (Math.round(feedback * 10) / (float)10);
            coachVO.setFeedback(feedback);
            coachVO.setLevel(coachDTO.getLevel());
            coachVOS.add(coachVO);
        }
        return coachVOS;
    }
}
