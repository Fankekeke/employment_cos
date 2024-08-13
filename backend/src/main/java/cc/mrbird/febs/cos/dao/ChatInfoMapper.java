package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ChatInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 沟通信息 mapper层
 *
 * @author FanK
 */
public interface ChatInfoMapper extends BaseMapper<ChatInfo> {

    /**
     * 分页获取沟通信息
     *
     * @param page     分页对象
     * @param chatInfo 沟通信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectChatPage(Page<ChatInfo> page, @Param("chatInfo") ChatInfo chatInfo);
}
