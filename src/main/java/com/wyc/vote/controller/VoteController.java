package com.wyc.vote.controller;


import com.wyc.vote.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("vote")
public class VoteController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("main")
    public String home(){
        return "admin";
    }

    //@GetMapping("/main2")
    @RequestMapping("/main2")
    public String home2(){
        return "admin";
    }

    @RequestMapping("/main3")
    public String home3(){
        return "admin";
    }


    @PostMapping("showlist")
    public void showList(HttpServletRequest req){
//        int PageNum = 5;//分页显示   每页五条数据
//        int PageTo = 1;//分页显示  第几页
//        String pageStr = req.getParameter("PageTo");
//        if(pageStr != null && !pageStr.equals(" ")){
//            PageTo = Integer.parseInt(pageStr);
//        }
//        //InfoDao infoDao= new InfoDao();
//        //int userid = user.getUser_id();//获取当前用户的ID vote_user id  赋值给user_add_ vote user_id
//        Integer userid = 0;
//        //int votecount = infoDao.searchuser_voteSum(userid); //查询当前用户投了多少选项
//        int votecount = userMapper.searchuser_voteSum(userid);
//
//        int[] IsVote = infoDao.searchvoteById(userid,votecount);//查询当前用户已投票的选项
//        List<Info> infolist = infoDao.showlist((PageTo-1)*5,PageNum);
//        int total = infoDao.searchAll();//数据总数
//        int page = (total+PageNum-1)/PageNum;//总页数
//
//        resp.setContentType("text/html;charset=utf-8");
//        resp.setCharacterEncoding("UTF-8");
//        JSONObject jo = new JSONObject();
//        jo.put("list", infolist);//转化为json格式
//        jo.put("PageTo", PageTo);//存值
//        jo.put("page", page);//存值
//        jo.put("IsVote", IsVote);//存值
//        resp.getWriter().write(jo.toJSONString());//传递给前台的ajax
    }

}
