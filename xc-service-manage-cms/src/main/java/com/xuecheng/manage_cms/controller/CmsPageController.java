package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageReuqust;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/cms/page/")
public class CmsPageController implements CmsPageControllerApi {


   @Autowired
   CmsPageService cmsPageService;

    @GetMapping("list/{page}/{size}")
    @Override
    public QueryResponseResult findPage(@PathVariable("page") int page,@PathVariable("size") int size,@RequestBody QueryPageReuqust queryPageRequest) {
       /*QueryResult queryResult = new QueryResult();
       queryResult.setTotal(2); //静态数据列表
       List list = new ArrayList();
       CmsPage cmsPage = new CmsPage();
       cmsPage.setPageName("测试页面");
       list.add(cmsPage);
       queryResult.setList(list);
       QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);*/
       return cmsPageService.findList(page,size,queryPageRequest);
       }


    @PostMapping("add")
    @Override
    public CmsPageResult addPage( @RequestBody CmsPage cmsPage) {
        return cmsPageService.addPage(cmsPage);
    }

    @PutMapping("update")
    @Override
    public CmsPageResult updatePage(String id, @RequestBody CmsPage cmsPage) {
        return  cmsPageService.updatePage(id,cmsPage);
    }

    @DeleteMapping("delete")
    @Override
    public ResponseResult deletePage(int pageid) {
        return  cmsPageService.deletePage(pageid);
    }


    @GetMapping("page/{id}")
    @Override
    public CmsPage findByIdPage(String id, @RequestBody CmsPage cmsPage) {
        return  cmsPageService.findByIdPage(id,cmsPage);
    }

    @PostMapping("page")
    @Override
    public ResponseResult postPage(int pageid) {
        return  cmsPageService.postPage(pageid);
    }

    @PostMapping("save")
    @Override
    public CmsPageResult savePage(@RequestBody CmsPage cmsPage) {
        return  cmsPageService.savePage(cmsPage);
    }

    @PostMapping("pageQuick")
    @Override
    public ResponseResult postPageQuick(@RequestBody CmsPage cmsPage) {
        return  cmsPageService.postPageQuick(cmsPage);
    }


}
