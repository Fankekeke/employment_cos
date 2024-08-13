package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ChatInfo;
import cc.mrbird.febs.cos.dao.ChatInfoMapper;
import cc.mrbird.febs.cos.service.IChatInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 沟通信息 实现层
 *
 * @author FanK
 */
@Service
public class ChatInfoServiceImpl extends ServiceImpl<ChatInfoMapper, ChatInfo> implements IChatInfoService {

}
