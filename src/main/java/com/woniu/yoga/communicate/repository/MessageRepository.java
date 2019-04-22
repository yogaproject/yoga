package com.woniu.yoga.communicate.repository;

import com.woniu.yoga.communicate.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description Message数据持久层
 * @Author guochxi
 * @Date 2019/4/20 21:20
 * @Version 1.0
 */
public interface MessageRepository extends JpaRepository<Message,Long> {
    /**
     * 根据会话id查找两人之间的聊天记录
     * @param conversionId
     * @return
     */
    List<Message> findByConversionId(String conversionId);

    /**
     * 查找两人会话的未读消息个数
     * @param conversionId
     * @param msgStatus
     * @return
     */
    Integer findByConversionIdAndMsgStatus(String conversionId,Integer msgStatus);

    /**
     * 根据用户id和通知类别查找通知记录并按时间降序
     * @param toId
     * @return
     */
    List<Message> findByToIdAndEntityTypeOrderBySendTimeDesc(Integer toId,Integer entityId);

}
