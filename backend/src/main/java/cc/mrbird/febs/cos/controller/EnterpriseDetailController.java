package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.EnterpriseDetail;
import cc.mrbird.febs.cos.service.IEnterpriseDetailService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业详情 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/enterprise-detail")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnterpriseDetailController {

    private final IEnterpriseDetailService enterpriseDetailService;

    /**
     * 分页获取企业详情信息
     *
     * @param page             分页对象
     * @param enterpriseDetail 企业详情信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<EnterpriseDetail> page, EnterpriseDetail enterpriseDetail) {
        return R.ok(enterpriseDetailService.selectEnterprisePage(page, enterpriseDetail));
    }

    /**
     * 查询企业详情信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(enterpriseDetailService.getById(id));
    }

    /**
     * 查询企业详情信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(enterpriseDetailService.list());
    }

    /**
     * 新增企业详情信息
     *
     * @param enterpriseDetail 企业详情信息
     * @return 结果
     */
    @PostMapping
    public R save(EnterpriseDetail enterpriseDetail) {
        return R.ok(enterpriseDetailService.save(enterpriseDetail));
    }

    /**
     * 修改企业详情信息
     *
     * @param enterpriseDetail 企业详情信息
     * @return 结果
     */
    @PutMapping
    public R edit(EnterpriseDetail enterpriseDetail) {
        return R.ok(enterpriseDetailService.updateById(enterpriseDetail));
    }

    /**
     * 删除企业详情信息
     *
     * @param ids ids
     * @return 企业详情信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(enterpriseDetailService.removeByIds(ids));
    }
}
