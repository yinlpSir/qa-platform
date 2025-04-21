package com.gjw.wdsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjw.wdsystem.dto.problem.PagingProblemDTO;
import com.gjw.wdsystem.exception.WdSystemException;
import com.gjw.wdsystem.mapper.*;
import com.gjw.wdsystem.pojo.Problem;
import com.gjw.wdsystem.pojo.User;
import com.gjw.wdsystem.service.ProblemService;
import com.gjw.wdsystem.utils.HotSpider;
import com.gjw.wdsystem.vo.HotProblemVO;
import com.gjw.wdsystem.vo.PagingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service("problemService")
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private HotSpider hotSpider;

    private static final String BASE64_ENCODE_ERROR_MESSAGE = "问题封面Base64编码异常!";
    private static final String BASE64_PREFIX = "data:image/png;base64,";

    @Override
    public ResponseEntity<Object> getProblem(String problemId) {
        Problem problem = problemMapper.getProblemById(problemId);
        if(Objects.isNull(problem)){
            return ResponseEntity.noContent().build(); // return ResponseEntity.status(HttpStatus.NO_CONTENT).body("未找到ID为"+problemId+"的问题");
        }
        problemMapper.modifyPageViewByProblemId(problemId);
        try {
            if(Objects.nonNull(problem.getCover())){
                problem.setCoverToString(BASE64_PREFIX+Base64.getEncoder().encodeToString(problem.getCover().readAllBytes()));
            }
        } catch (IOException e) {
            throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
        }
        return ResponseEntity.ok(this.buildResult("获取成功!",problem));
    }

    /**
     * 这里直接从数据库随机获取15条问题了
     * @param userId
     * @return
     */
    @Override
    public ResponseEntity<Object> getProblemsYouLike(String userId) {
        List<Problem> problems = problemMapper.getRandomProblems();
        return ResponseEntity.ok(this.buildResult("获取成功",problems));
    }

    /**
     * 这里是通过点赞数和评论数来决定其热门度，点赞数和评论数的权重分别为0.5。
     * @return
     */
    @Override
    public ResponseEntity<Object> getPopularProblems() {
        return ResponseEntity.ok(this.buildResult("获取成功",problemMapper.getPopularProblems()));
    }

    /**
     * 获取最新的问题
     * 这里就是直接获取最近一周发布的问题,然后降序排序
     * @return
     */
    @Override
    public ResponseEntity<Object> getLatestProblems() {
        return ResponseEntity.ok(this.buildResult("获取成功",problemMapper.getLatestProblems()));
    }

    @Override
    public ResponseEntity<Object> getFollowedUserProblems(String userId,PagingProblemDTO pagingProblemDTO) {
        PageHelper.startPage(pagingProblemDTO.getCurrentPage(),pagingProblemDTO.getPageSize());

        List<Problem> followedUserProblems = problemMapper.getFollowedUserProblems(userId);

        PageInfo<Problem> results = new PageInfo<>(followedUserProblems);

        results.getList().forEach(problem -> {
            if(Objects.nonNull(problem.getCover())){
                try {
                    problem.setCoverToString(BASE64_PREFIX+Base64.getEncoder().encodeToString(problem.getCover().readAllBytes()));
                } catch (IOException e) {
                    throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
                }
            }
        });

        PagingVO<Problem> pagingVO = new PagingVO<>(
                results.getTotal(),
                results.getPages(),
                results.getPageNum(),
                results.getPageSize(),
                results.getList()
        );
        return ResponseEntity.ok(this.buildResult("获取成功",pagingVO));
    }

    @Override
    public ResponseEntity<Object> getProblemByUserId(String userId) {
        Problem problem = problemMapper.getProblemByUserId(userId);
        if(Objects.isNull(problem)){
            return ResponseEntity.noContent().build(); // return ResponseEntity.status(HttpStatus.NO_CONTENT).body("未找到ID为"+problemId+"的问题");
        }
        try {
            if(Objects.nonNull(problem.getCover())){
                problem.setCoverToString(BASE64_PREFIX+Base64.getEncoder().encodeToString(problem.getCover().readAllBytes()));
            }
        } catch (IOException e) {
            throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
        }
        return ResponseEntity.ok(this.buildResult("获取成功!",problem));
    }

    /**
     * 该热门问题是从知乎上爬下来的
     * @return
     */
    @Override
    public ResponseEntity<Object> getHotProblems() {
        List<HotProblemVO> hotProblems = hotSpider.getAllHotProblems();
        return ResponseEntity.ok(this.buildResult("获取成功!",hotProblems));
    }

    @Override
    public ResponseEntity<Object> getRecommendProblems(PagingProblemDTO pagingProblemDTO) {
        /*
         * startPage(int pageNum, int pageSize)
         *  pageNum 页码。这个页码得从第一页开始,如果为0是搜不到内容的
         *  pageSize 每页显示多少条数据
         */
        PageHelper.startPage(pagingProblemDTO.getCurrentPage(),pagingProblemDTO.getPageSize());

        List<Problem> problems = problemMapper.getRecommendProblems();

        PageInfo<Problem> results = new PageInfo<>(problems);

        var dataList = new ArrayList<Object>(); // 要返回给前端的数据

        // 加工一下数据
        results.getList().forEach( p -> {
            User user = userMapper.getUserById(p.getUserId());
            var tempMap = new HashMap<String,Object>();
            if (Objects.nonNull(user)){
                try {
                    tempMap.put("headPortrait",BASE64_PREFIX+Base64.getEncoder().encodeToString(user.getHeadPortrait().readAllBytes()));
                } catch (IOException e) {
                    throw new RuntimeException("用户头像Base64编码异常!",e);
                }
                tempMap.put("username",user.getUsername());
                tempMap.put("gender",user.getGender());
            }
            try {
                if(Objects.nonNull(p.getCover())){
                    p.setCoverToString(BASE64_PREFIX+Base64.getEncoder().encodeToString(p.getCover().readAllBytes()));
                }
            } catch (IOException e) {
                throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
            }
            // isCollected 是否被当前用户收藏
            tempMap.put("problem",p);
            tempMap.put("likesCount",likesMapper.getLikesCountByProblemId(p.getId()));
            tempMap.put("commentCount",commentMapper.getCommentCountByProblemId(p.getId()));
            dataList.add(tempMap);
        });

        // 封装 查询结果集对象
        PagingVO<Object> pagingVO = new PagingVO<>(
                results.getTotal(),
                results.getPages(),
                results.getPageNum(),
                results.getPageSize(),
                dataList
        );

        return ResponseEntity.ok(this.buildResult("获取成功!",pagingVO));
    }

    @Override
    public ResponseEntity<Object> getMineProblems(String userId, PagingProblemDTO pagingProblemDTO) {
        PageHelper.startPage(pagingProblemDTO.getCurrentPage(),pagingProblemDTO.getPageSize());
        List<Problem> problems = problemMapper.getMineProblemsByUserId(userId);
        PageInfo<Problem> result = new PageInfo<>(problems);

        result.getList().forEach(problem -> {
            try {
                if(Objects.nonNull(problem.getCover())){
                    problem.setCoverToString(BASE64_PREFIX+Base64.getEncoder().encodeToString(problem.getCover().readAllBytes()));
                }
            } catch (IOException e) {
                throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
            }
        });

        // 封装查询结果集
        PagingVO<Problem> pagingVO = new PagingVO<>(
                result.getTotal(),
                result.getPages(),
                result.getPageNum(),
                result.getPageSize(),
                result.getList()
        );
        return ResponseEntity.ok(this.buildResult("获取成功!",pagingVO));
    }

    @Override
    public ResponseEntity<Object> searchProblems(PagingProblemDTO pagingProblemDTO) {
        PageHelper.startPage(pagingProblemDTO.getCurrentPage(),pagingProblemDTO.getPageSize());
        List<Problem> problems = problemMapper.getProblemsByKeyword(pagingProblemDTO.getKeyword());
        PageInfo<Problem> result = new PageInfo<>(problems);

        result.getList().forEach(problem -> {
            try {
                if(Objects.nonNull(problem.getCover())){
                    problem.setCoverToString(BASE64_PREFIX+Base64.getEncoder().encodeToString(problem.getCover().readAllBytes()));
                }
            } catch (IOException e) {
                throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
            }
        });

        // 封装查询结果集
        PagingVO<Problem> pagingVO = new PagingVO<>(
                result.getTotal(),
                result.getPages(),
                result.getPageNum(),
                result.getPageSize(),
                result.getList()
        );
        return ResponseEntity.ok(this.buildResult("搜索成功",pagingVO));
    }

    @Override
    public ResponseEntity<Object> create(String title, String content, MultipartFile cover, User user) throws IOException {
        Problem problem = new Problem();
        problem.setId(UUID.randomUUID().toString().replace("-", ""));
        if(Objects.nonNull(cover)){
            problem.setCover(cover.getInputStream());
        }
        problem.setTitle(title);
        problem.setContent(content);
        problem.setUserId(user.getId());
        int effectRows = problemMapper.insertProblem(problem);
        if(effectRows > 0) return ResponseEntity.ok(this.buildResult("创建成功!",null));
        throw new WdSystemException("创建出错!");
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delProblem(String problemId) throws RuntimeException {
        int affectedRows = problemMapper.delProblemByProblemId(problemId); // 删除问题
//        int a= 2 % 0;
        collectionMapper.delCollectsByProblemId(problemId); // 删除收藏
        likesMapper.delLikesByProblemId(problemId); // 删除点赞
        commentMapper.delCommentsByProblemId(problemId); // 删除评论
        return ResponseEntity.ok(this.buildResult("删除成功",null));
    }

    /**
     * 构造 返回结果集 方法
     * @Nullable 主要用于指示一个方法、属性或参数可以为空值。
     * @param message 响应消息
     * @param data 响应数据
     * @return
     */
    private HashMap<String,Object> buildResult(String message, @Nullable Object data){
        var result = new HashMap<String,Object>();
        result.put("message",message);
        result.put("data",data);
        return result;
    }
}
