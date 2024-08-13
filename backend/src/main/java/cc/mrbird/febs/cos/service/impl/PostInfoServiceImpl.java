package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.dao.PostInfoMapper;
import cc.mrbird.febs.cos.service.IPostInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 岗位管理 实现层
 *
 * @author FanK
 */
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements IPostInfoService {

}
