package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CollectInfo;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.service.ICollectInfoService;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 企业收藏 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/collect-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CollectInfoController {

    private final ICollectInfoService collectInfoService;

    private final IExpertInfoService expertInfoService;

    /**
     * 分页获取企业收藏信息
     *
     * @param page        分页对象
     * @param collectInfo 企业收藏信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<CollectInfo> page, CollectInfo collectInfo) {
        return R.ok(collectInfoService.selectCollectPage(page, collectInfo));
    }

    /**
     * 根据用户获取收藏岗位
     *
     * @param userId 学生ID
     * @return 结果
     */
    @GetMapping("/queryCollectByUser/{userId}")
    public R queryCollectByUser(@PathVariable("userId") Integer userId) {
        ExpertInfo expertInfo = expertInfoService.getOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getUserId, userId));
        List<CollectInfo> collectList = collectInfoService.list(Wrappers.<CollectInfo>lambdaQuery().eq(CollectInfo::getType, 3)
                .eq(CollectInfo::getExpertId, expertInfo.getId()));
        return R.ok(CollectionUtil.isEmpty(collectList) ? Collections.emptyList() : collectList.stream().map(CollectInfo::getBaseId).collect(Collectors.toList()));
    }

    /**
     * 取消岗位收藏
     * @param collectInfo 收藏信息
     * @return 结果
     */
    @PutMapping("/sendNotCollect")
    public R sendNotCollect(CollectInfo collectInfo) {
        ExpertInfo expertInfo = expertInfoService.getOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getUserId, collectInfo.getExpertId()));
        return R.ok(collectInfoService.remove(Wrappers.<CollectInfo>lambdaQuery()
                .eq(CollectInfo::getBaseId, collectInfo.getBaseId()).eq(CollectInfo::getExpertId, expertInfo.getId())));
    }

    /**
     * 查询企业收藏信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(collectInfoService.getById(id));
    }

    /**
     * 查询企业收藏信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(collectInfoService.list());
    }

    /**
     * 新增企业收藏信息
     *
     * @param collectInfo 企业收藏信息
     * @return 结果
     */
    @PostMapping
    public R save(CollectInfo collectInfo) {
        ExpertInfo expertInfo = expertInfoService.getOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getUserId, collectInfo.getExpertId()));
        if (expertInfo != null) {
            collectInfo.setExpertId(expertInfo.getId());
            collectInfo.setExpertCode(expertInfo.getCode());
        }
        collectInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(collectInfoService.save(collectInfo));
    }

    /**
     * 修改企业收藏信息
     *
     * @param collectInfo 企业收藏信息
     * @return 结果
     */
    @PutMapping
    public R edit(CollectInfo collectInfo) {
        return R.ok(collectInfoService.updateById(collectInfo));
    }

    /**
     * 删除企业收藏信息
     *
     * @param ids ids
     * @return 企业收藏信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(collectInfoService.removeByIds(ids));
    }
}
