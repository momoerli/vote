package com.wyc.vote.controller;


import com.alibaba.fastjson.JSONObject;
import com.wyc.vote.entity.Info;
import com.wyc.vote.entity.User;
import com.wyc.vote.mapper.UserMapper;
import com.wyc.vote.service.InfoService;
import com.wyc.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("vote")
public class VoteController {

    @Autowired
    UserMapper userMapper;


    @Autowired
    VoteService voteService;

    @Autowired
    InfoService infoService;

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

    /**
     * 提交投票
     */
    @RequestMapping("doVote")
    public void doVote(HttpServletRequest req, HttpServletResponse resp){
        boolean b = false;
        String vote_type = req.getParameter("vote_type");//投票类型
        String vote_id = req.getParameter("vote_id");//用于标明哪个被投票

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userid = user.getUserId();
        if(vote_type.equals("0")){//单选类型
            String temp_id = req.getParameter("checkb");//checkb是单选框  value值为选项id
            //b =infodao.singleVote(temp_id); //计入投票
            b =infoService.singleVote(temp_id); //计入投票
            //infodao.updateTicketSumById(vote_id);//计入投票总数
            infoService.updateTicketSumById(vote_id);//计入投票总数

        }else if(vote_type.equals("1")){
            int count = infodao.searchinfoCountById(vote_id);//查询当前id的选项数量count（choose_id）
            for (int i = 1; i <= count; i++) {
                String inputname = i+"";//将i转换成字符串
                String temp_id = req.getParameter(inputname);
                if(temp_id!=null){
                    b=infodao.singleVote(temp_id);//计入投票
                    infodao.updateTicketSumById(vote_id);//计入投票总数
                }
            }
        }
        if(b){
            infodao.updatePersonsumById(vote_id);//更新投票人数
            infodao.adduserVote(userid,vote_id);//标记用户投票
            resp.getWriter().print("<script>var r=confirm('是否查看投票结果');if(r==true){window.location.href='VoteServlet?flag=showVote&vote_id="+vote_id+"';}else{window.location.href='admin.jsp';}</script>");
        }else{
            resp.getWriter().print("<script>alert('投票失败'); history.go(-1); </script>");
        }
    }

    @RequestMapping("voteInterface")
    public String voteInterface(HttpServletRequest req, HttpServletResponse resp){
        //投票页面
        String vote_id = req.getParameter("vote_id");
        List<Info> infolist = infoService.searchinfoById(vote_id);


        req.setAttribute("infolist", infolist);
        req.setAttribute("infocount", infolist.size());
        //req.getRequestDispatcher("Vote.jsp").forward(req, resp);
        return "Vote";
    }


    @RequestMapping("showlist")
    public void showList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int PageNum = 5;//分页显示   每页五条数据
        int PageTo = 1;//分页显示  第几页
        String pageStr = req.getParameter("PageTo");
        if(pageStr != null && !pageStr.equals(" ")){
            PageTo = Integer.parseInt(pageStr);
        }
//        //InfoDao infoDao= new InfoDao();
//        //int userid = user.getUser_id();//获取当前用户的ID vote_user id  赋值给user_add_ vote user_id
        Integer userid = 0;
//        //int votecount = infoDao.searchuser_voteSum(userid); //查询当前用户投了多少选项
        int votecount = userMapper.searchuser_voteSum(userid);
//
//      int[] IsVote = infoDao.searchvoteById(userid,votecount);//查询当前用户已投票的选项
        int[] IsVote = voteService.searchvoteById(userid,votecount);


//        List<Info> infolist = infoDao.showlist((PageTo-1)*5,PageNum);
        List<Info> infolist = voteService.showlist((PageTo-1)*5,PageNum);


//        int total = infoDao.searchAll();//数据总数
        int total = infoService.searchAll();//数据总数
        int page = (total+PageNum-1)/PageNum;//总页数
//
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        JSONObject jo = new JSONObject();
        jo.put("list", infolist);//转化为json格式
        jo.put("PageTo", PageTo);//存值
        jo.put("page", page);//存值
        jo.put("IsVote", IsVote);//存值
        resp.getWriter().write(jo.toJSONString());//传递给前台的ajax
    }

}
