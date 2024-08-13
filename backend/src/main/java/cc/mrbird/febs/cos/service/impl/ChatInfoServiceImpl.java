package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ChatInfo;
import cc.mrbird.febs.cos.dao.ChatInfoMapper;
import cc.mrbird.febs.cos.service.IChatInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 沟通信息 实现层
 *
 * @author FanK
 */
@Service
public class ChatInfoServiceImpl extends ServiceImpl<ChatInfoMapper, ChatInfo> implements IChatInfoService {

    /**
     * 分页获取沟通信息
     *
     * @param page     分页对象
     * @param chatInfo 沟通信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectChatPage(Page<ChatInfo> page, ChatInfo chatInfo) {
        return baseMapper.selectChatPage(page, chatInfo);
    }
}
