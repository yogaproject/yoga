package com.woniu.yoga.communicate.repository;

import com.woniu.yoga.communicate.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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
     * @Date 15:35 2019/4/23
     * @Param [userId, conversationId]
     * @return java.lang.Integer
     **/
    @Query(value = "select count(1) from message where to_id =?1 and conversation_id =?2 and m.msgStatus = 0 and m.msgFlag = 0",nativeQuery = true)
    Integer getUnreadCount(Integer userId,String conversationId);

    /**
     * @Description 根据会话id查找两人之间的聊天记录
     * @Author guochxi
     * @Date 15:02 2019/4/22
     * @Param [conversionId]
     * @return java.util.List<com.gcx.jap_mybatis_test.entity.Message>
     **/
    @Query(value = "select * from message where conversation_id =?1 and msg_flag=0",nativeQuery = true)
    List<Message> findByConversationId(String conversationId);

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

    /**
     * @Description 根据用户id和消息类别查找消息列表并按时间降序
     * @Author guochxi
     * @Date 13:33 2019/4/23
     * @Param [userId, entityType]
     * @return java.util.List<com.woniu.yoga.communicate.pojo.Message>
     **/
    @Query(value = "select * from (select * from message where entity_type = ?2 and (from_id =?1 or to_id =?1) order by send_time desc) tt group by conversation_id order by send_time desc ",nativeQuery = true)
    List<Message> findByUserIdAndEntityType(Integer userId,Integer entityType);
}