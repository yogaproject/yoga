package com.woniu.yoga.communicate.repository;

import com.woniu.yoga.communicate.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description Message数据持久层
 * @Author guochxi
 * @Date 2019/4/20 21:20
 * @Version 1.0
 */
public interface MessageRepository extends JpaRepository<Message,Long> {
    /**
     * @Description 查询某个对话的未读消息个数
     * @Author guochxi
     * @Date 15:00 2019/4/22
     * @Param [fromId, toId, msgStatus, msgFlag]
     * @return java.lang.Integer
     **/

    @Query("select count(1) from Message m where m.fromId =?1 and m.toId=?2 and m.msgStatus = 0 and m.msgFlag = 0")
    Integer queryCountByFromIdAndToId(Integer fromId,Integer toId);

    /**
     * @Description 根据会话id查找两人之间的聊天记录
     * @Author guochxi
     * @Date 15:02 2019/4/22
     * @Param [conversionId]
     * @return java.util.List<com.gcx.jap_mybatis_test.entity.Message>
     **/
    List<Message> findByConversationId(String conversationId);

    /**
     * @Description 根据用户id和通知类别查找通知记录并按时间降序
     * @Author guochxi
     * @Date 15:04 2019/4/22
     * @Param [toId, entityId]
     * @return java.util.List<com.gcx.jap_mybatis_test.entity.Message>
     **/
    List<Message> findByToIdAndEntityTypeOrderBySendTimeDesc(Integer toId,Integer entityId);

    /**
     * @Description 将与fromId的对话设置为已读
     * @Author guochxi
     * @Date 17:48 2019/4/22
     * @Param [fromId, toId]
     * @return int
     **/
    @Modifying
    @Query(value = "update Message m set m.msgStatus = 1 where m.fromId =?1 and m.toId =?2")
    int updateMsgStatusByFromIdAndToId(Integer fromId,Integer toId);

    /**
     * @Description 删除与fromId的聊天记录
     * @Author guochxi
     * @Date 17:49 2019/4/22
     * @Param [fromId, toId]
     * @return int
     **/
    @Modifying
    @Query(value = "update Message m set m.msgFlag = 1 where m.fromId =?1 and m.toId =?2")
    int updateMsgFlagByFromIdAndToId(Integer fromId,Integer toId);
}