package com.ccsu.jsn.service.impl;

import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.dao.ContentMapper;
import com.ccsu.jsn.dao.EnclosuresMapper;
import com.ccsu.jsn.pojo.Content;
import com.ccsu.jsn.pojo.Enclosures;
import com.ccsu.jsn.service.IContentService;
import com.ccsu.jsn.util.FTPUtil;
import com.ccsu.jsn.vo.ContentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-06 20:23
 */
@Service
public class ContentServiceImpl implements IContentService {

    Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);
    Map<String, String> map = new HashMap<>();
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private EnclosuresMapper enclosuresMapper;

    Content content = new Content();

    @Override
    public Result upload(MultipartFile file, String path, long id,long userId) {
        initFileClass();

        if ("success".equals(fileUpload(file, path).getMsg())) {
            content.setUserId(userId);
            content.setId(id);
            logger.info("content:{}", content);

            contentMapper.update(content);
        }
        return Result.success(0);
    }

    @Override
    public Result uploadEnclosure(MultipartFile file, long contentId, String path,long userId) {
        Enclosures enclosures = new Enclosures();
        /**原文件名*/
        String fileName = file.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf('.') + 1);

        String uploadFileName = UUID.randomUUID().toString() + "." + fileSuffix;
        String url = "/upload/enclosures/" + uploadFileName;

        logger.info("fimeName={},url={}", fileName, url);
        path = path + "/enclosures";
        System.out.println(path);
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return Result.error("上传失败");
        }

        enclosures.setId(1008001);
        enclosures.setUserId(userId);
        enclosures.setContentId(contentId);
        enclosures.setName(fileName);
        enclosures.setUrl(url);
        enclosuresMapper.insert(enclosures);

        return Result.success(0);
    }

    @Override
    public Result getContent(long menuId) {
        ContentVo contentVo = new ContentVo();
        content = contentMapper.selectByParentId(menuId);
        if (content != null) {
            List<Enclosures> enclosuresList = enclosuresMapper.getEnclosuresListByContentId(content.getId());
            contentVo.setEnclosuresList(enclosuresList);
        }
        contentVo.setContent(content);
        logger.info("on {} print {}", this.getClass().getName(), content);
        return Result.success(contentVo);
    }

    private void initFileClass() {
        map.put("jpg", "img");
        map.put("png", "img");
        map.put("jpeg", "img");
        map.put("mp4", "video");
        map.put("java", "code");
        map.put("txt", "text");
        map.put("doc", "text");
        map.put("docx", "text");
        map.put("pdf", "text");
    }

    private Result fileUpload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();

        String fileSuffix = fileName.substring(fileName.lastIndexOf('.') + 1);

        String uploadFileName = UUID.randomUUID().toString() + "." + fileSuffix;
        System.out.println("开始上传");
        logger.info("开始上传文件，上传的文件名：{}，上传的路径：{}，新文件名：{} ", fileName, path, uploadFileName);
        logger.info("文件类型为：{}", map.get(fileSuffix));
        path = path + "/" + map.get(fileSuffix);
        System.out.println(path);
        content.set(map.get(fileSuffix), "/upload/" + map.get(fileSuffix) + "/" + uploadFileName);
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path, uploadFileName);

        try {
            file.transferTo(targetFile);
            //将targetFiile上传到FTP服务器
//            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

            //删除upload下的文件
//            logger.info("删除upload下文件结果：{}", targetFile.delete());
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return Result.error("上传失败");
        }
        return Result.success(path);
    }


}
