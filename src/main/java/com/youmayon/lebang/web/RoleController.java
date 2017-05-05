package com.youmayon.lebang.web;

import com.youmayon.lebang.enums.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jawinton on 17/05/04.
 */
@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController {
    /**
     * 角色列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Role[] list() {
        return Role.values();
    }
}
