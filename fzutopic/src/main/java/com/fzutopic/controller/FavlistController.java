package com.fzutopic.controller;


import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.dao.FavlistItemDao;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Favlist;
import com.fzutopic.model.FavlistItemExample;
import com.fzutopic.model.FavlistItemKey;
import com.fzutopic.service.FavlistServiceImpl;
import com.fzutopic.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class FavlistController {

    @Resource(name = "favlistServiceImpl")
    FavlistServiceImpl favlistService;
    @Resource
    FavlistItemDao favlistItemDao;

    //根据userid获取收藏夹列表,1403
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/favlist/page/{page}")
    public @ResponseBody
    AjaxResponse getFavlists(@PathVariable int page,
                             HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        PageInfo<Favlist> favlists = favlistService.getFavlists(userid, page);
        return AjaxResponse.success(favlists);
    }

    //根据favlistid获取收藏内容列表,1403
    //根据favlistid获取收藏内容列表,1403
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/favlist/item/{favlistid}/page/{page}")
    public @ResponseBody
    AjaxResponse getFavlistItems(@PathVariable String favlistid, @PathVariable int page) {
        PageInfo<FavlistItemKey> favlistItemKeys = favlistService.getFavlistItems(favlistid, page);
        return AjaxResponse.success(favlistItemKeys);
    }

    //新建收藏夹,1403
    @UserLoginToken
    @CrossOrigin
    @PostMapping("/favlist")
    public @ResponseBody
    AjaxResponse createFavlist(@RequestBody Favlist favlist, HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        favlist.setUserid(userid);
        return AjaxResponse.success(favlistService.createFavlist(favlist));
    }

    //添加某个收藏内容,1403
    @UserLoginToken
    @CrossOrigin
    @PostMapping("/favlist/item")
    public @ResponseBody
    AjaxResponse createFavlistItem(@RequestBody FavlistItemKey favlistItemKey) {
        return AjaxResponse.success(favlistService.createFavlistItem(favlistItemKey));
    }

    //删除某个收藏夹,1403
    @UserLoginToken
    @CrossOrigin
    @DeleteMapping("/favlist")
    public @ResponseBody
    AjaxResponse deleteFavlist(@RequestParam String favlistid) {
        favlistService.deleteFavlist(favlistid);
        return AjaxResponse.success(favlistid);
    }

    /**
     * 根据收藏内容id取消收藏
     *
     * @param collectedid
     * @param httpServletRequest
     * @return
     * @author 呼叫哆啦A梦
     */
    @UserLoginToken
    @CrossOrigin
    @DeleteMapping("/favlist/item")
    public @ResponseBody
    AjaxResponse deleteFavlistItem(@RequestParam String collectedid, HttpServletRequest httpServletRequest) {

        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        List<Favlist> favlists = favlistService.getAllFavlist(userid);
        String message = "未找到此收藏内容，故未删除任何收藏";
        for (Favlist favlist : favlists
        ) {
            String favlistid = favlist.getFavlistid();
            FavlistItemKey favlistItemKey = new FavlistItemKey();
            favlistItemKey.setFavlistid(favlistid);
            favlistItemKey.setCollectedid(collectedid);

            if (favlistService.deleteFavlistItem(favlistItemKey) == 1) {
                message = "删除成功";
                break;
            }

        }
        return AjaxResponse.success(message);
    }
}
