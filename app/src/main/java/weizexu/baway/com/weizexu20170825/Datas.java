package weizexu.baway.com.weizexu20170825;

import java.util.List;

public class Datas{
    String date ;
    String content ;
    String head ;
    int id ;
    String name ;
    String image;
    List<Commot> commotList;

    public Datas() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Datas(String image) {
        this.image = image;
    }

    public List<Commot> getCommotList() {
        return commotList;
    }

    public void setCommotList(List<Commot> commotList) {
        this.commotList = commotList;
    }

    public Datas(List<Commot> commotList) {
        this.commotList = commotList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Commot {
        String content;
        int userid;

        public Commot() {
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }
}