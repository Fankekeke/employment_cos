package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.NotifyInfo;
import cc.mrbird.febs.cos.dao.NotifyInfoMapper;
import cc.mrbird.febs.cos.service.INotifyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 消息通知 实现层
 *
 * @author FanK
 */
@Service
public class NotifyInfoServiceImpl extends ServiceImpl<NotifyInfoMapper, NotifyInfo> implements INotifyInfoService {

}
