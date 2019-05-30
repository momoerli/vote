package com.wyc.vote.service;

import com.wyc.vote.entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VoteService {


    @Autowired
    JdbcTemplate jdbcTemplate;

    //主页列表显示
    public List<Info> showlist(int begin, int num){
        List<Info> infolist = new ArrayList<>();
        String sql = "select  v.vote_id,v.vote_title, v.vote_sum, count(v1.choose_id) choose_sum, ANY_VALUE(user_name) user_name " +
                "from vote_info v,vote_choose v1,user_add_vote v2 left join vote_user  " +
                "on id = user_id where v.choose_id = v1.choose_id and v2.vote_id = v.vote_id group by v.vote_id";
        sql += " limit " + begin + "," + num + " ";


        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        for(int i=0;i<result.size();i++){
            Map<String, Object> map = result.get(i);

            Info info = new Info();
            info.setVote_id(Integer.valueOf(map.get("vote_id")+""));
            info.setVote_title(map.get("vote_title")+"");
            info.setVote_sum(Integer.valueOf(map.get("vote_sum")+""));

            info.setUser_name(map.get("user_name")+"");
            info.setChoose_sum(Integer.valueOf(map.get("choose_sum")+""));
            infolist.add(info);
        }


//        Connection conn =  JDBCUtil.getConnection();
//        try {
//            stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                //实例化实体类
//                Info info = new Info();
//                info.setVote_id(rs.getInt("vote_id"));
//                info.setVote_title(rs.getString("vote_title"));
//                info.setVote_sum(rs.getInt("vote_sum"));
//
//                info.setUser_name(rs.getString("user_name"));
//                info.setChoose_sum(rs.getInt("choose_sum"));
//                //把数据加入集合范类
//                infolist.add(info);
//            }
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }finally {
//            JDBCUtil.close(conn, stmt, null, rs);
//        }
        return infolist;
    }



    public int[] searchvoteById(int userid, int votecount) {

        String sql="select vote_id from user_add_vote where user_id='"+userid+"'";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        int[] a = new int[votecount + 1];
        for(int i=0;i<result.size();i++){
            a[i] = Integer.valueOf(result.get(i).get("vote_id")+"");
        }

//        Connection conn = JDBCUtil.getConnection();

//        try {
//            //编译sql语句
//            stmt = conn.prepareStatement(sql);
//            //执行sql语句
//            rs = stmt.executeQuery(sql);
//            int i = 0;
//            while (rs.next()) {
//                a[i] = rs.getInt("vote_id");
//                i++;
//            }
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }
        return a;
    }
}
