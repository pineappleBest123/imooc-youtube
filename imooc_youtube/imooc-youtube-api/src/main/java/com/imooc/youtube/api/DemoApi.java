package com.imooc.youtube.api;

import com.imooc.youtube.domain.JsonResponse;
import com.imooc.youtube.domain.UserInfo;
import com.imooc.youtube.domain.Video;
import com.imooc.youtube.service.DemoService;
import com.imooc.youtube.service.ElasticSearchService;
import com.imooc.youtube.service.util.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class DemoApi {

    @Autowired
    private DemoService demoService;

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/query")
    public Long query(Long id){
        return demoService.query(id);
    }

    @GetMapping("/slices")
    public void slices(MultipartFile file) throws Exception {
        fastDFSUtil.convertFileToSlices(file);
    }

    @GetMapping("/es-videos")
    public JsonResponse<Video> getEsVideos(@RequestParam String keyword){
        Video video = elasticSearchService.getVideos(keyword);
        return new JsonResponse<>(video);
    }

    @PostMapping("/es-videos")
    public JsonResponse<String> addVideos(@RequestBody Video video){
        elasticSearchService.addVideo(video);
        return JsonResponse.success();
    }

    @DeleteMapping("/es-videos")
    public JsonResponse<String> deleteVideos(){
        elasticSearchService.deleteAllVideos();
        return JsonResponse.success();
    }

    @PostMapping("/es-users")
    public JsonResponse<String> addUsers(@RequestBody UserInfo userInfo){
        elasticSearchService.addUserInfo(userInfo);
        return JsonResponse.success();
    }



}
