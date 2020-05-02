package com.fzutopic.controller;


import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Favlist;
import com.fzutopic.model.FavlistItemKey;
import com.fzutopic.service.FavlistServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
public class FavlistController {

    @Resource(name="favlistServiceImpl")
    FavlistServiceImpl favlistService;

    //根据userid获取收藏夹列表
    @GetMapping("/favlist/{userid}")
    public @ResponseBody AjaxResponse getFavlists(@PathVariable String userid){
        return AjaxResponse.success(favlistService.getFavlists(userid));
    }

    //根据favlistid获取收藏内容列表
    @GetMapping("/favlist/item/{favlistid}")
    public @ResponseBody AjaxResponse getFavlistItems(@PathVariable String favlistid){
        return AjaxResponse.success(favlistService.getFavlistItems(favlistid));
    }

    //新建收藏夹
    @PostMapping("/favlist")
    public @ResponseBody AjaxResponse createFavlist(@RequestBody Favlist favlist){
        return AjaxResponse.success(favlistService.createFavlist(favlist));
    }

    //添加某个收藏内容
    @PostMapping("/favlist/item")
    public @ResponseBody AjaxResponse createFavlistItem(@RequestBody FavlistItemKey favlistItemKey){
        return AjaxResponse.success(favlistService.createFavlistItem(favlistItemKey));
    }

    //删除某个收藏夹
    @DeleteMapping("/favlist")
    public @ResponseBody AjaxResponse deleteFavlist(@RequestParam String favlistid){
        favlistService.deleteFavlist(favlistid);
        return AjaxResponse.success(favlistid);
    }

    //取消收藏某个内容
    @DeleteMapping("/favlist/item")
    public @ResponseBody AjaxResponse pushFavlistItem(@RequestParam String favlistid,
                                                      @RequestParam String collectedid){
        return AjaxResponse.success(favlistService.pushFavlistItem(favlistService.getFavlistItem(collectedid)));
    }
}
