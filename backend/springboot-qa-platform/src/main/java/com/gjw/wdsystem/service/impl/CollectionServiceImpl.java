package com.gjw.wdsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjw.wdsystem.dto.collect.PagingCollectDTO;
import com.gjw.wdsystem.exception.WdSystemException;
import com.gjw.wdsystem.mapper.CollectionMapper;
import com.gjw.wdsystem.pojo.Collection;
import com.gjw.wdsystem.service.CollectionService;
import com.gjw.wdsystem.vo.PagingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public ResponseEntity<Object> getCollectedByUserId(String userId, PagingCollectDTO pagingCollectDTO) {
        PageHelper.startPage(pagingCollectDTO.getCurrentPage(),pagingCollectDTO.getPageSize());
        List<Collection> collected = collectionMapper.getCollectedByUserId(userId);
        PageInfo<Collection> pageInfo = new PageInfo<>(collected);

        // 封装结果集
        PagingVO pagingVO = new PagingVO(
                pageInfo.getTotal(),
                pageInfo.getPages(),
                pageInfo.getPageNum(),
                pageInfo.getPageSize(),
                pageInfo.getList()
        );
        return ResponseEntity.ok(this.buildResult("获取成功",pagingVO));
    }

    @Override
    public ResponseEntity<Object> collectionProblem(String userId, String problemId) {
        int affectedRows = collectionMapper.insertCollection(
                UUID.randomUUID().toString().replace("-", ""),
                userId,
                problemId
        );
        if(affectedRows >= 1)
            return ResponseEntity.ok(this.buildResult("收藏成功",null));
        throw new WdSystemException("收藏失败");
    }

    @Override
    public ResponseEntity<Object> getCountByUserId(String userId) {
        return ResponseEntity.ok(this.buildResult("获取成功",collectionMapper.getCountByUserId(userId)));
    }

    @Override
    public ResponseEntity<Object> getCountByProblemId(String problemId) {
        return ResponseEntity.ok(this.buildResult("获取成功",collectionMapper.getCountByProblemId(problemId)));
    }

    @Override
    public ResponseEntity<Object> cancelCollection(String userId,String problemId) {
        int affectedRows = collectionMapper.delCollection(userId,problemId);
        return ResponseEntity.ok(this.buildResult("已取消",null));
    }

    /**
     * 判断该用户是否收藏该商品
     * @param userId
     * @param problemId
     * @return
     */
    @Override
    public ResponseEntity<Object> isUserCollectedProblem(String userId, String problemId) {
        Collection collection = collectionMapper.checkUserCollectedProblem(userId,problemId);
        return ResponseEntity.ok(collection == null?false:true);
    }

    private HashMap<String,Object> buildResult(String message, @Nullable Object data){
        var result = new HashMap<String,Object>();
        result.put("message",message);
        result.put("data",data);
        return result;
    }
}
