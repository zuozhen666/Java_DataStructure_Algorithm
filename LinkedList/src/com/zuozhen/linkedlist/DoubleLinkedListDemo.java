package com.zuozhen.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        //修改
        //HeroNode2 newhero4 = new HeroNode2(4,"公孙胜","入云龙");
        //doubleLinkedList.update(newhero4);
        //doubleLinkedList.list();
        //删除
        //doubleLinkedList.delete(4);
        //doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历
    public void list() {
        if(head.next == null) {
            System.out.println("NULL");
            return;
        }
        HeroNode2 tmp = head.next;
        while(true) {
            if(tmp == null) {
                break;
            }
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }
    //添加
    public void add(HeroNode2 heroNode) {
        HeroNode2 tmp = head;
        while(true) {
            if(tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = heroNode;
        heroNode.pre = tmp;
    }
    //修改节点信息，根据no
    public void update(HeroNode2 heroNode) {
        if(head.next == null) {
            System.out.println("list is empty");
            return;
        }
        HeroNode2 tmp = head.next;
        boolean flag = false;//表示是否找到
        while(true) {
            if(tmp == null) {
                break;
            }
            if(tmp.no == heroNode.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if(flag) {
            tmp.name = heroNode.name;
            tmp.nickname = heroNode.nickname;
        }
        else {
            System.out.println("not found");
        }
    }

    //删除
    public void delete(int no) {
        if(head.next == null) {
            System.out.println("list is null");
            return;
        }
        HeroNode2 tmp = head.next;
        boolean flag = false;//是否找到
        while(true) {
            if(tmp == null) {
                break;
            }
            if(tmp.no == no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if(flag) {
            tmp.pre.next = tmp.next;
            if(tmp.next != null){
                tmp.next.pre = tmp.pre;
            }
        } else {
            System.out.println("not found");
        }
    }
}

//节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    //构造器
    public HeroNode2(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //重写toString
    @Override
    public String toString() {
        return "HeroNode2 [no=" + no + ", name=" + name + ", nickname=" + nickname+"]";
    }
}