package com.fzutopic.controller;


import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Favlist;
import com.fzutopic.model.FavlistItemKey;
import com.fzutopic.service.FavlistServiceImpl;
import com.fzutopic.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class FavlistController {

    @Resource(name="favlistServiceImpl")
    FavlistServiceImpl favlistService;

    //根据userid获取收藏夹列表,1403
    @UserLoginToken
    @GetMapping("/favlist/{userid}")
    public @ResponseBody AjaxResponse getFavlists(@PathVariable String userid){
        PageInfo<Favlist> favlists=favlistService.getFavlists(userid);
        return AjaxResponse.success(favlists);
    }

    //根据favlistid获取收藏内容列表,1403
    @UserLoginToken
    @GetMapping("/favlist/item/{favlistid}")
    public @ResponseBody AjaxResponse getFavlistItems(@PathVariable String favlistid){
        PageInfo<FavlistItemKey> favlistItemKeys=favlistService.getFavlistItems(favlistid);
        return AjaxResponse.success(favlistItemKeys);
    }

    //新建收藏夹,1403
    @UserLoginToken
    @PostMapping("/favlist")
    public @ResponseBody AjaxResponse createFavlist(@RequestBody Favlist favlist, HttpServletRequest httpServletRequest){
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        favlist.setUserid(userid);
        return AjaxResponse.success(favlistService.createFavlist(favlist));
    }

    //添加某个收藏内容,1403
    @UserLoginToken
    @PostMapping("/favlist/item")
    public @ResponseBody AjaxResponse createFavlistItem(@RequestBody FavlistItemKey favlistItemKey){
        return AjaxResponse.success(favlistService.createFavlistItem(favlistItemKey));
    }

    //删除某个收藏夹,1403
    @UserLoginToken
    @DeleteMapping("/favlist")
    public @ResponseBody AjaxResponse deleteFavlist(@RequestParam String favlistid){
        favlistService.deleteFavlist(favlistid);
        return AjaxResponse.success(favlistid);
    }

    //取消收藏某个内容,1403
    @UserLoginToken
    @DeleteMapping("/favlist/item")
    public @ResponseBody AjaxResponse pushFavlistItem(@RequestParam String favlistid,
                                                      @RequestParam String collectedid){
        return AjaxResponse.success(favlistService.pushFavlistItem(favlistService.getFavlistItem(collectedid)));
    }
}
