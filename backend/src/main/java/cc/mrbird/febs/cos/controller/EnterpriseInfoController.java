package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import cc.mrbird.febs.cos.service.IEnterpriseInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业信息管理 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/enterprise-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnterpriseInfoController {

    private final IEnterpriseInfoService enterpriseInfoService;

    /**
     * 分页获取企业信息信息
     *
     * @param page           分页对象
     * @param enterpriseInfo 企业信息信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<EnterpriseInfo> page, EnterpriseInfo enterpriseInfo) {
        return R.ok(enterpriseInfoService.selectEnterprisePage(page, enterpriseInfo));
    }

    /**
     * 查询企业信息信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(enterpriseInfoService.getById(id));
    }

    /**
     * 查询企业信息信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(enterpriseInfoService.list());
    }

    /**
     * 新增企业信息信息
     *
     * @param enterpriseInfo 企业信息信息
     * @return 结果
     */
    @PostMapping
    public R save(EnterpriseInfo enterpriseInfo) {
        return R.ok(enterpriseInfoService.save(enterpriseInfo));
    }

    /**
     * 修改企业信息信息
     *
     * @param enterpriseInfo 企业信息信息
     * @return 结果
     */
    @PutMapping
    public R edit(EnterpriseInfo enterpriseInfo) {
        return R.ok(enterpriseInfoService.updateById(enterpriseInfo));
    }

    /**
     * 删除企业信息信息
     *
     * @param ids ids
     * @return 企业信息信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(enterpriseInfoService.removeByIds(ids));
    }
}
