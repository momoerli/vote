package com.wyc.vote.service;

import com.wyc.vote.entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class InfoService {

    @Autowired
    JdbcTemplate jdbcTemplate;



    public List<Info> searchlist(String data) {
        List<Info> infolist = new ArrayList<Info>();
        // 定义sql语句
        String sql = "select v.vote_id,v.vote_title,v.vote_sum,count(v1.choose_info) choose_count " +
                "from vote_info v,vote_choose v1 " +
                "where v.choose_id=v1.choose_id AND v.vote_title LIKE '%"
                + data + "%' GROUP BY v.vote_id";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            Info info = new Info();
            info.setVote_id(Integer.valueOf(map.get("vote_id")+""));
            info.setVote_title(map.get("vote_title")+"");

            info.setVote_sum(Integer.valueOf(map.get("vote_sum")+""));
            info.setChoose_sum(Integer.valueOf(map.get("choose_count")+""));
            infolist.add(info);
        }


//        // 连接数据库
//        Connection conn = JDBCUtil.getConnection();
//        // 编译sql语句
//        try {
//            stmt = conn.prepareStatement(sql);
//            // 执行sql语句
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                info = new Info();
//                info.setVote_id(rs.getInt("vote_id"));
//                info.setVote_title(rs.getString("vote_title"));
//                info.setVote_sum(rs.getInt("vote_sum"));
//                info.setChoose_sum(rs.getInt("choose_count"));
//                infolist.add(info);
//            }
//            // 获取结果集
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        } finally {
//            // 关闭数据库
//            JDBCUtil.close(conn, stmt, null, rs);
//        }
        return infolist;

    }

    //查询是否已投票的信息
    public int[] searchvoteById(int userid, int votecount) {
        int[] a = new int[votecount + 1];
        String sql="select vote_id from user_add_vote where user_id='"+userid+"'";


        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            a[i] = Integer.valueOf(map.get("vote_id")+"");
        }

//        Connection conn = JDBCUtil.getConnection();
//        int[] a = new int[votecount + 1];
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

    //查询当前用户投了多少选项
    public int searchuser_voteSum(int userid) {
        String sql="select count(*) count from user_add_vote where user_id='"+userid+"'";
        int flag = 0;

        flag = jdbcTemplate.queryForObject(sql,Integer.class);


//        Connection conn = JDBCUtil.getConnection();
//        int flag = 0;
//
//        try {
//            //编译sql语句
//            stmt = conn.prepareStatement(sql);
//            //执行sql语句
//            rs = stmt.executeQuery(sql);
//            if (rs.next()) {
//                flag = rs.getInt("count");
//            }
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }

        return flag;
    }

    //用于记录发起投票的人  查询vote_info表中的vote_id
    public int searchVote_id(int c_id) {
        String sql = "select * from vote_info where choose_id='"+c_id+"'";

        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        int i = Integer.valueOf(map.get("vote_id")+"");

//        Connection conn=JDBCUtil.getConnection();
//        int i=0;
//        try {
//            stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery(sql);
//            if (rs.next()) {
//                i = rs.getInt("vote_id");
//            }
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            //关闭数据库
//            JDBCUtil.close(conn, stmt, null, rs);
//        }
        return i;
    }
    //标记投票内容与投票人的联系  添加vote_id和user_id到user_add_vote表
    public boolean addUser_add_vote(int vote_id,int user_id ) {
        String sql="insert into user_add_vote(user_id,vote_id) VALUES (?,?) ";

        int i = jdbcTemplate.update(sql, user_id,vote_id);

//        Connection conn=JDBCUtil.getConnection();
//        int i = 0;
//        try {
//            //编译sql语句
//            psmt = conn.prepareStatement(sql);
//            psmt.setInt(1, vote_id);
//            psmt.setInt(2, user_id);
//            i = psmt.executeUpdate();
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }finally {
//            //关闭数据库
//            JDBCUtil.close(conn, stmt, psmt, rs);
//        }
        return i>0;
    }



    //投票信息新增 加入vote_choose表
    public boolean addmessage(int c_id,int rowC,String[] s) {
        //PreparedStatement psmt = null;
        String sql="insert into vote_choose(choose_id,choose_info) values(?,?)";

        int i = 0;
        for (int j = 0; j < rowC; j++) {
            i = jdbcTemplate.update(sql, c_id, s[j]);
        }

//        Connection conn=JDBCUtil.getConnection();
//
//        try {
//            //编译sql语句
//            for (int j = 0; j < rowC; j++) {
//                psmt = conn.prepareStatement(sql);
//                psmt.setInt(1, c_id);
//                psmt.setString(2, s[j]);
//                i = psmt.executeUpdate();
//            }
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }finally {
//            //关闭数据库
//            JDBCUtil.close(conn, stmt, psmt, rs);
//        }
        return i>0;

    }


    //增加投票到vote_info表
    public int addinfo(String vote_title,String vote_type) {
        //随机数
        Random r = new Random();
        int choose_id = r.nextInt(99999999);//随机数范围0-999998
        //PreparedStatement psmt = null;
        String sql = "insert into vote_info(vote_title,vote_type,choose_id) values(?,?,?)";


        jdbcTemplate.update(sql,vote_title,vote_type,choose_id);
        //链接数据库
//        Connection conn=JDBCUtil.getConnection();
//        int i = 0 ;
//        //编译sql语句
//        try {
//            psmt = conn.prepareStatement(sql);
//            psmt.setString(1, vote_title);
//            psmt.setString(2, vote_type);
//            psmt.setInt(3, choose_id);
//            //i表示更新了多少行
//            i = psmt.executeUpdate();
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }finally {
//            JDBCUtil.close(conn, stmt, psmt, rs);
//        }
        return choose_id;
    }



    //查询到选项和投票人数
    public List<Info> showVote(String vote_id) {
        List<Info> infolist = new ArrayList<Info>();
        String sql = "SELECT * from vote_info v,vote_choose v1 where v.choose_id=v1.choose_id and vote_id='" + vote_id
                + "'";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            Info info = new Info();
            info.setVote_title(map.get("vote_title")+"");
            info.setChoose_info(map.get("choose_info")+"");
            info.setChoose_num(Integer.valueOf(map.get("choose_num")+""));
            info.setTicket_sum(Integer.valueOf(map.get("ticket_sum")+""));
            info.setVote_id(Integer.valueOf(map.get("vote_id")+""));
            info.setTemp_id(Integer.valueOf(map.get("id")+""));
            infolist.add(info);
        }

//        //连接数据库
//        Connection conn = JDBCUtil.getConnection();
//        //编译sql语句
//        try {
//            stmt = conn.prepareStatement(sql);
//            //执行sql语句
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                info = new Info();
//                info.setVote_title(rs.getString("vote_title"));
//                info.setChoose_info(rs.getString("choose_info"));
//                info.setChoose_num(rs.getInt("choose_num"));
//                info.setTicket_sum(rs.getInt("ticket_sum"));
//                info.setVote_id(rs.getInt("vote_id"));
//                info.setTemp_id(rs.getInt("id"));
//                infolist.add(info);
//            }
//            //获取结果集
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }finally {
//            //关闭数据库
//            JDBCUtil.close(conn, stmt, null, rs);
//        }
        return infolist;
    }


    //标记用户投票
    public boolean adduserVote(int userid, String vote_id) {
//        PreparedStatement psmt = null;
//		String sql = "INSERT into user_vote_info (user_id,vote_id) values(?,?)";
        String sql = "INSERT into user_add_vote (user_id,vote_id) values(?,?)";
        int i = jdbcTemplate.update(sql, userid, vote_id);
//        Connection conn = JDBCUtil.getConnection();
//        int i = 0;
//
//        try {
//            // 编译sql语句
//
//            psmt = conn.prepareStatement(sql);
//            psmt.setInt(1, userid);
//            psmt.setString(2, vote_id);
//            i = psmt.executeUpdate();
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        } finally {
//            // 关闭数据库
//            JDBCUtil.close(conn, null, psmt, rs);
//        }
        return i > 0;


    }


    // 根据vote_info 表的id更新投票人数
    public void updatePersonsumById(String vote_id) {
        String sql = "update vote_info SET vote_sum=1+vote_sum WHERE vote_id=?";
        jdbcTemplate.update(sql,vote_id);
//        PreparedStatement psmt = null;
//        Connection conn = JDBCUtil.getConnection();
//        try {
//            // 编译sql语句
//            psmt = conn.prepareStatement(sql);
//            psmt.setString(1, vote_id);
//            psmt.executeUpdate();
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        } finally {
//            // 关闭数据库
//            JDBCUtil.close(conn, null, psmt, rs);
//        }


    }


    //查询选项数量
    public int searchinfoCountById(String vote_id) {
        //String sql="select count(choose_info) count from vote_choose where choose_id=(select choose_id from vote_info where vote_id = "+vote_id+")";
        String sql = "SELECT count(*) count from vote_info v,vote_choose v1 WHERE v.choose_id=v1.choose_id  and v.vote_id= '"
                + vote_id + "'";
//        Connection conn = JDBCUtil.getConnection();
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);

        int flag = 0;
        //编译sql语句
//        try {
//            stmt = conn.prepareStatement(sql);
//            //执行sql语句
//            rs = stmt.executeQuery(sql);
//            if(rs.next()) {
//                flag = rs.getInt("count");
//            }
//            //获取结果集
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }finally {
//            //关闭数据库
//            JDBCUtil.close(conn, stmt, null, rs);
//        }

        flag = result;
        return flag;
    }


    //新投票计入投票总数
    public void updateTicketSumById(String vote_id) {
        String sql = "update vote_info set ticket_sum=ticket_sum+1 where vote_id=?";
//        Connection conn = JDBCUtil.getConnection();
        jdbcTemplate.update(sql, vote_id);
//
//        try {
//            psmt = conn.prepareStatement(sql);
//            psmt.setString(1, vote_id);
//            psmt.executeUpdate();
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }finally {
//            //关闭数据库
//            JDBCUtil.close(conn, null, psmt, rs);
//        }


    }


    //新投票计入投票
    public boolean singleVote(String temp_id) {
        String sql = "UPDATE vote_choose SET choose_num=choose_num+1 WHERE id=?";
        int i = jdbcTemplate.update(sql, temp_id);
//        Connection conn = JDBCUtil.getConnection();
//        int i = 0;
//        try {
//            //编译sql语句
//            psmt = conn.prepareStatement(sql);
//            psmt.setString(1, temp_id);
//            i = psmt.executeUpdate();
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }finally {
//            //关闭数据库
//            JDBCUtil.close(conn, stmt, psmt, rs);
//        }
        return i > 0;
    }


    //查询投票功能
    public List<Info> searchinfoById(String vote_id) {

        List<Info> infolist = new ArrayList<>();
        String sql = "SELECT v.vote_id,v1.id,v.vote_type,v.vote_title,v1.choose_info,v1.choose_num,v.vote_sum from vote_info v,vote_choose v1 WHERE v.choose_id=v1.choose_id  and v.vote_id='"
                + vote_id + "'";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            Info info = new Info();
            info.setVote_title(map.get("vote_title") + "");
            info.setChoose_info(map.get("choose_info") + "");
            info.setChoose_num(Integer.valueOf(map.get("choose_num") + ""));
            info.setVote_sum(Integer.valueOf(map.get("vote_sum") + ""));
            info.setTemp_id(Integer.valueOf(map.get("id") + ""));
            info.setVote_type(Integer.valueOf(map.get("vote_type") + ""));
            info.setVote_id(Integer.valueOf(map.get("vote_id") + ""));
            infolist.add(info);
        }


        //连接数据库
        //Connection conn = JDBCUtil.getConnection();
        //编译sql语句
//        try {
//            stmt = conn.prepareStatement(sql);
//            //执行sql语句
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                info = new Info();
//                info.setVote_title(rs.getString("vote_title"));
//                info.setChoose_info(rs.getString("choose_info"));
//                info.setChoose_num(rs.getInt("choose_num"));
//                info.setVote_sum(rs.getInt("vote_sum"));
//                info.setTemp_id(rs.getInt("id"));
//                info.setVote_type(rs.getInt("vote_type"));
//                info.setVote_id(rs.getInt("vote_id"));
//                infolist.add(info);
//            }
//            //获取结果集
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }finally {
//            //关闭数据库
//            JDBCUtil.close(conn, stmt, null, rs);
//        }
        return infolist;
    }

    //查询信息数量
    public int searchAll() {
        String sql = "select count(*) count from vote_info";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
//        Connection conn = JDBCUtil.getConnection();
        int flag = 0;
//        try {
//            //编译
//            stmt = conn.prepareStatement(sql);
//            //执行
//            rs = stmt.executeQuery(sql);
//            if (rs.next()) {
//                flag = rs.getInt("count");
//            }
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }
        flag = count;
        return flag;
    }
}
