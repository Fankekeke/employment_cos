package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.NotifyInfo;
import cc.mrbird.febs.cos.dao.NotifyInfoMapper;
import cc.mrbird.febs.cos.service.INotifyInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 消息通知 实现层
 *
 * @author FanK
 */
@Service
public class NotifyInfoServiceImpl extends ServiceImpl<NotifyInfoMapper, NotifyInfo> implements INotifyInfoService {

    /**
     * 分页获取消息通知信息
     *
     * @param page       分页对象
     * @param notifyInfo 消息通知信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectNotifyPage(Page<NotifyInfo> page, NotifyInfo notifyInfo) {
        return baseMapper.selectNotifyPage(page, notifyInfo);
    }

    /**
     * 根据用户ID获取通知消息
     *
     * @param userCode 用户ID
     * @return 结果
     */
    @Override
    public List<NotifyInfo> selectNotifyByUserId(String userCode) {
        // 查询用户编号
        if (StrUtil.isEmpty(userCode)) {
            return Collections.emptyList();
        }
        return this.list(Wrappers.<NotifyInfo>lambdaQuery().eq(NotifyInfo::getUserCode, userCode).eq(NotifyInfo::getDelFlag, 0));
    }
}
