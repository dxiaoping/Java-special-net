package com.ccsu.javalearn.service.impl;

import com.ccsu.javalearn.common.Const;
import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.dao.KnownMapper;
import com.ccsu.javalearn.dao.MenuMapper;
import com.ccsu.javalearn.dao.ResourceMapper;
import com.ccsu.javalearn.pojo.Known;
import com.ccsu.javalearn.pojo.Resource;
import com.ccsu.javalearn.pojo.User;
import com.ccsu.javalearn.service.IFileService;
import com.ccsu.javalearn.service.IKnownService;
import com.ccsu.javalearn.service.IResourceService;
import com.ccsu.javalearn.util.IdFactory;
import com.ccsu.javalearn.vo.KnownVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-12-02 21:18
 */
@Service
public class KnownServiceImpl implements IKnownService {

    @Autowired
    private KnownMapper knownMapper;

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private IFileService fileService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IdFactory idFactory;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Result saveKnown(Known known, long menuIId, MultipartFile fileDoc, MultipartFile fileImg, String path) {
        if (fileDoc == null) {
            known.setAttachment(null);
            knownMapper.update(known);
        } else {
            String url = fileService.upload(fileDoc, path);
            known.setAttachment(url);
        }

        String name = menuMapper.selectById(menuIId).getName();
        known.setName(name);
        knownMapper.update(known);
//        }

        if (fileImg != null) {
            String url = fileService.upload(fileImg, path);
            Resource resource = new Resource(known.getId(), known.getUserId(), known.getName() + "知识模块图片", path, Const.ResourceType.KNOWN_IMG);
            resourceService.addResource(fileImg, resource);
        }
        return Result.success(0);
    }

    @Override
    public Result getKnown(long menuId, User user) {
        KnownVo knownVo = new KnownVo();
        Known known = knownMapper.selectByMenuId(menuId);
        if (known == null) {
            if (user.getRole() == Const.Role.ROLE_STUDENT) {
                return Result.error("该模块没有内容，请联系教师或管理员创建");
            }
            long id = idFactory.createExample();
            String name = menuMapper.selectById(menuId).getName();
            Known known1 = new Known(id, menuId, user.getPhone());
            known1.setName(name);
            knownMapper.insert(known1);
        }
        List<Resource> resourceList = resourceMapper.selectResourceByMenuId(known.getId());
        knownVo.setKnown(known);
        knownVo.setResources(resourceList);
        return Result.success(knownVo);
    }

    @Override
    public Known getKnownById(long id) {
        return knownMapper.selectById(id);
    }
}
