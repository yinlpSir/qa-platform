package com.gjw.wdsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjw.wdsystem.dto.comment.AddCommentDTO;
import com.gjw.wdsystem.dto.comment.PagingCommentDTO;
import com.gjw.wdsystem.mapper.CommentMapper;
import com.gjw.wdsystem.pojo.Comment;
import com.gjw.wdsystem.service.CommentService;
import com.gjw.wdsystem.vo.PagingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResponseEntity<Object> getCommentsCount(String problemId) {
        return ResponseEntity.ok(this.buildResult("获取成功",commentMapper.getCommentCountByProblemId(problemId)));
    }

    @Override
    public ResponseEntity<Object> getLatelyCommentCount(String problemId) {
        return ResponseEntity.ok(this.buildResult("获取成功",commentMapper.getLatelyCommentCount(problemId)));
    }

    @Override
    public ResponseEntity<Object> getCommentsCountByUserId(String userId) {
        return ResponseEntity.ok(this.buildResult("获取成功",commentMapper.getCommentCountByUserId(userId)));
    }

    @Override
    public ResponseEntity<Object> addComment(AddCommentDTO addCommentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(addCommentDTO,comment);
        comment.setId(UUID.randomUUID().toString().replace("-", ""));
        int affectedRows = commentMapper.insertComment(comment);
        return ResponseEntity.ok(this.buildResult("发布成功",null));
    }

    @Override
    public ResponseEntity<Object> getCommentsByProblemId(String problemId) {
        List<Comment> comments = commentMapper.getCommentsByProblemId(problemId);
        return ResponseEntity.ok(this.buildResult("获取成功",comments));
    }

    @Override
    public ResponseEntity<Object> getCommentsByUserId(String userId, PagingCommentDTO pagingCommentDTO) {
        PageHelper.startPage(pagingCommentDTO.getCurrentPage(),pagingCommentDTO.getPageSize());
        List<Comment> comments = commentMapper.getCommentsByUserId(userId);
        PageInfo<Comment> result = new PageInfo<>(comments);
        PagingVO pagingVO = new PagingVO(
                result.getTotal(),
                result.getPages(),
                result.getPageNum(),
                result.getPageSize(),
                result.getList()
        );
        return ResponseEntity.ok(this.buildResult("获取成功",pagingVO));
    }

    @Override
    public ResponseEntity<Object> delComment(String commentId) {
        commentMapper.delCommentById(commentId);
        return ResponseEntity.ok(this.buildResult("删除成功",null));
    }

    private HashMap<String,Object> buildResult(String message, @Nullable Object data){
        var result = new HashMap<String,Object>();
        result.put("message",message);
        result.put("data",data);
        return result;
    }
}
