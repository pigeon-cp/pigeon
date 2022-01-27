package com.github.taccisum.pigeon.controller;

import com.github.taccisum.pigeon.core.data.MassTacticDO;
import com.github.taccisum.pigeon.core.entity.core.MassTactic;
import com.github.taccisum.pigeon.core.entity.core.MessageMass;
import com.github.taccisum.pigeon.core.repo.MassTacticRepo;
import com.github.taccisum.pigeon.dao.data.MassTacticDOImpl;
import com.github.taccisum.pigeon.dto.CreateTacticRequest;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 消息群发相关接口
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@RequestMapping("/masses")
@RestController
@Transactional(rollbackFor = Exception.class)
public class MassController {
    @Resource
    private MassTacticRepo massTacticRepo;

    @ApiOperation("创建一个群发策略")
    @PostMapping("/tactics/{type}")
    public long create(@PathVariable(required = false) String type, @RequestBody CreateTacticRequest dto) {
        if (StringUtils.isBlank(type)) {
            type = "DEFAULT";
        }
        MassTacticDO o = new MassTacticDOImpl();
        o.setType(type);
        o.setMustTest(dto.getMustTest());
        o.setDefaultSender(dto.getDefaultSender());

        String targets = dto.getTargets();
        if (targets.startsWith("file://")) {
            o.setSource(targets.replaceFirst("file://", ""));
            o.setSourceType(MassTactic.SourceType.FILE);
        } else if (targets.startsWith("(http|https)://")) {
            o.setSource(targets.replaceFirst("(http|https)://", ""));
            o.setSourceType(MassTactic.SourceType.URL);
        } else {
            o.setSource(targets);
            o.setSourceType(MassTactic.SourceType.TEXT);
        }

        o.setTemplateId(dto.getTemplateId());
        o.setDefaultParams(dto.getDefaultParams());
        MassTactic tactic = massTacticRepo.create(o);
        return tactic.id();
    }

    @ApiOperation("测试群发策略（仅发送给白名单用户）")
    @PostMapping("/tactics/{id}/test")
    public long test(@PathVariable long id) {
        MassTactic tactic = massTacticRepo.getOrThrow(id);
        MessageMass mass = tactic.test();
        return mass.id();
    }

    @ApiOperation("群发策略执行准备工作")
    @PostMapping("/tactics/{id}/prepare")
    public long prepare(@PathVariable long id) {
        MassTactic tactic = massTacticRepo.getOrThrow(id);
        MessageMass mass = tactic.prepare();
        return mass.id();
    }

    @ApiOperation("执行群发策略")
    @PostMapping("/tactics/{id}/execute")
    public long execute(@PathVariable long id, @RequestParam Boolean boost) {
        MassTactic tactic = massTacticRepo.getOrThrow(id);
        MessageMass mass = tactic.exec(Optional.ofNullable(boost).orElse(false));
        return mass.id();
    }
}
