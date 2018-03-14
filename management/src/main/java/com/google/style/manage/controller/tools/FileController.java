package com.google.style.manage.controller.tools;


import com.google.style.manage.common.controller.BaseController;
import com.google.style.config.GlobalConfig;
import com.google.style.model.tools.FileDO;
import com.google.style.service.tools.FileService;
import com.google.style.utils.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author liangz
 * @email xx@.com
 * @date 2018-03-12 14:39:46
 */
 
@Controller
@RequestMapping("/tools/fileUpload")
public class FileController extends BaseController{

	@Autowired
	private FileService fileService;

	@Autowired
	private GlobalConfig globalConfig;
	
	@GetMapping()
	@RequiresPermissions("tools:fileUpload")
	String File(){
	    return "common/file/file";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("tools:fileUpload")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FileDO> fileList = fileService.list(query);
		int total = fileService.count(query);
		PageUtils pageUtils = new PageUtils(fileList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("common:file:add")
	String add(){
	    return "common/file/file/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:file:edit")
	String edit(@PathVariable("id") Long id,Model model){
		FileDO file = fileService.get(id);
		model.addAttribute("file", file);
	    return "common/file/file/edit";
	}
	

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("common:info")
    public R info(@PathVariable("id") Long id) {
        FileDO sysFile = fileService.get(id);
        return R.ok().put("sysFile", sysFile);
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:save")
    public R save(FileDO sysFile) {
        if (fileService.save(sysFile) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("common:update")
    public R update(@RequestBody FileDO sysFile) {
        fileService.update(sysFile);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    // @RequiresPermissions("common:remove")
    public R remove(Long id, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = globalConfig.getUploadPath() + fileService.get(id).getUrl().replace("/files/", "");
        if (fileService.remove(id) > 0) {
            boolean b = FileUtil.deleteFile(fileName);
            if (!b) {
                return R.error("数据库记录删除成功，文件删除失败");
            }
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("common:remove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        fileService.batchRemove(ids);
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/upload")
    R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), globalConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return R.error();
        }

        if (fileService.save(sysFile) > 0) {
            return R.ok().put("fileName",sysFile.getUrl());
        }
        return R.error();
    }
}
