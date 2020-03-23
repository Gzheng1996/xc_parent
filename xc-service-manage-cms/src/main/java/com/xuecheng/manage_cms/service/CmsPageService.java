package com.xuecheng.manage_cms.service;


import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageReuqust;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CmsPageService {

    @Autowired
    CmsPageRepository cmsPageRepository;


    public QueryResponseResult findList(int page, int size, QueryPageReuqust queryPageRequest){

        if(page<=0){
            page=1;
        }
        size=size-1;
        if(size==0){
            size=20;
        }
        ExampleMatcher exampleMatcher1 = ExampleMatcher.matching();
        exampleMatcher1.withMatcher("PageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        CmsPage cmsPage = new CmsPage();
        if(StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        if(StringUtils.isNotEmpty(queryPageRequest.getPageName())){
            cmsPage.setPageName(queryPageRequest.getPageName());
        }
        if(StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }

        Example<CmsPage> objectExample = Example.of(cmsPage,exampleMatcher1);
        //分页对象
        Pageable pageable = PageRequest.of(page, size); //分页查询
        Page<CmsPage> all = cmsPageRepository.findAll(objectExample,pageable);
        QueryResult<CmsPage> cmsPageQueryResult = new QueryResult<CmsPage>();
        cmsPageQueryResult.setList(all.getContent());
        cmsPageQueryResult.setTotal(all.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,cmsPageQueryResult);
    }


    public CmsPageResult addPage(CmsPage cmsPage) {
        if(cmsPage==null){
            return new CmsPageResult(CmsCode.CMS_GENERATEHTML_DATAISNULL,cmsPage);
        }
        CmsPage byPageName = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),cmsPage.getSiteId(),cmsPage.getPageWebPath());
        if(byPageName!=null){
            cmsPage.setPageId(null);//添加页面主键由spring data 自动
            return new CmsPageResult(CmsCode.CMS_GENERATEHTML_DATAISNULL,null);
        }
        CmsPage save = cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS,save);
    }

    public CmsPageResult updatePage(String id, CmsPage cmsPage) {
        Optional<CmsPage> byId = cmsPageRepository.findById(id);
        if(byId.isPresent()){
            cmsPage.setPageId(byId.get().getPageId());
            CmsPage insert = cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS,insert);
        }
        return new CmsPageResult(CommonCode.SUCCESS,null);

    }

    public ResponseResult deletePage(int pageid) {
        cmsPageRepository.deleteById(pageid+"");
        return new CmsPageResult(CommonCode.SUCCESS,null);
    }

    public CmsPage findByIdPage(String id, CmsPage cmsPage) {
        return cmsPageRepository.findById(id).get();
    }

    public ResponseResult postPage(int pageid) {
        return null;
    }

    public CmsPageResult savePage(CmsPage cmsPage) {
        return null;
    }

    public ResponseResult postPageQuick(CmsPage cmsPage) {
        return null;
    }
}
