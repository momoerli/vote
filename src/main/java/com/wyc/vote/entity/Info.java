package com.wyc.vote.entity;

public class Info {
    private int vote_id;
    private String vote_title;//投票标题
    private int choose_id;
    private int vote_sum;//投票总数
    private int vote_type;//投票类型
    private String choose_info;//选项内容
    private int choose_sum;//选项数量
    private int choose_num;//单个选项投票数量
    private int temp_id;//用于获取投票选项的id（就是choose中的id）
    private int ticket_sum;//票总数
    private String user_name;//用于标记投票发起人


    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public String getVote_title() {
        return vote_title;
    }

    public void setVote_title(String vote_title) {
        this.vote_title = vote_title;
    }

    public int getChoose_id() {
        return choose_id;
    }

    public void setChoose_id(int choose_id) {
        this.choose_id = choose_id;
    }

    public int getVote_sum() {
        return vote_sum;
    }

    public void setVote_sum(int vote_sum) {
        this.vote_sum = vote_sum;
    }

    public int getVote_type() {
        return vote_type;
    }

    public void setVote_type(int vote_type) {
        this.vote_type = vote_type;
    }

    public String getChoose_info() {
        return choose_info;
    }

    public void setChoose_info(String choose_info) {
        this.choose_info = choose_info;
    }

    public int getChoose_sum() {
        return choose_sum;
    }

    public void setChoose_sum(int choose_sum) {
        this.choose_sum = choose_sum;
    }

    public int getChoose_num() {
        return choose_num;
    }

    public void setChoose_num(int choose_num) {
        this.choose_num = choose_num;
    }

    public int getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(int temp_id) {
        this.temp_id = temp_id;
    }

    public int getTicket_sum() {
        return ticket_sum;
    }

    public void setTicket_sum(int ticket_sum) {
        this.ticket_sum = ticket_sum;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "Info [vote_id=" + vote_id + ", vote_title=" + vote_title + ", choose_id=" + choose_id + ", vote_sum="
                + vote_sum + ", vote_type=" + vote_type + ", choose_info=" + choose_info + ", choose_sum=" + choose_sum
                + ", choose_num=" + choose_num + ", temp_id=" + temp_id + ", ticket_sum=" + ticket_sum + ", user_name="
                + user_name + "]";
    }


}
