package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.IndustryInfo;
import cc.mrbird.febs.cos.service.IIndustryInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 所属行业 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/industry-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndustryInfoController {

    private final IIndustryInfoService industryInfoService;

    /**
     * 分页获取所属行业信息信息
     *
     * @param page         分页对象
     * @param industryInfo 所属行业信息信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<IndustryInfo> page, IndustryInfo industryInfo) {
        return R.ok(industryInfoService.selectIndustryPage(page, industryInfo));
    }

    /**
     * 查询所属行业信息信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(industryInfoService.getById(id));
    }

    /**
     * 查询所属行业信息信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(industryInfoService.list());
    }

    /**
     * 新增所属行业信息信息
     *
     * @param industryInfo 所属行业信息信息
     * @return 结果
     */
    @PostMapping
    public R save(IndustryInfo industryInfo) {
        industryInfo.setCode("IDT-" + System.currentTimeMillis());
        industryInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(industryInfoService.save(industryInfo));
    }

    /**
     * 修改所属行业信息信息
     *
     * @param industryInfo 所属行业信息信息
     * @return 结果
     */
    @PutMapping
    public R edit(IndustryInfo industryInfo) {
        return R.ok(industryInfoService.updateById(industryInfo));
    }

    /**
     * 删除所属行业信息信息
     *
     * @param ids ids
     * @return 所属行业信息信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(industryInfoService.removeByIds(ids));
    }
}
