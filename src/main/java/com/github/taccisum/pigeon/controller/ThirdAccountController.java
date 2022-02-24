package com.github.taccisum.pigeon.controller;

import com.github.taccisum.pigeon.dao.data.ThirdAccountDOImpl;
import com.github.taccisum.pigeon.dto.CreateThirdAccountRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pigeon.core.repo.ThirdAccountRepo;

import javax.annotation.Resource;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Validated
@RestController
@Transactional
@RequestMapping("/third-accounts")
public class ThirdAccountController {
    @Resource
    private ThirdAccountRepo repo;

    @PostMapping
    public long create(@RequestBody CreateThirdAccountRequest body) {
        ThirdAccountDOImpl data = new ThirdAccountDOImpl();
        BeanUtils.copyProperties(body, data);
        return repo.create(data).id();
    }
}
