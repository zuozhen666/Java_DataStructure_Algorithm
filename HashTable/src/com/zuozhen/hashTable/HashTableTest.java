package com.zuozhen.hashTable;


import java.util.Scanner;

import static java.lang.System.exit;

/**
 * 哈希表代码实现
 * 面试题：使用id不借助数据库存储和查询雇员信息
 */
public class HashTableTest {
    public static void main(String[] args) {
        //创建哈希表
        HashTable hashTable = new HashTable(7);

        //测试菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add");
            System.out.println("list");
            System.out.println("find");
            System.out.println("exit");

            key = scanner.next();
            switch(key) {
                case "add":
                    System.out.println("input id");
                    int id = scanner.nextInt();
                    System.out.println("input name");
                    String name = scanner.next();
                    Employee employee = new Employee(id,name);
                    hashTable.add(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("input id");
                    int findId = scanner.nextInt();
                    hashTable.findEmployeeById(findId);
                    break;
                case "exit":
                    scanner.close();
                    exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

//创建哈希表，管理多条链表
class HashTable {
    private EmployeeLinkedList[] employeeLinkedListArray;
    private int size;

    //构造器
    public HashTable(int size) {
        //初始化
        this.size = size;
        employeeLinkedListArray = new EmployeeLinkedList[size];
        //分别初始化每一条链表
        for(int i = 0; i <size;i++){
            employeeLinkedListArray[i] = new EmployeeLinkedList();
        }
    }

    //添加雇员
    public void add(Employee employee) {
        //根据员工的id得到该员工应该加入到哪条链表
        int employeeLinkedListNO = hashFun(employee.id);
        employeeLinkedListArray[employeeLinkedListNO].add(employee);
    }

    //遍历所有链表，遍历哈希表，《链表数组》
    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmployeeById(int id) {
        //利用散列函数确定去哪条链表查找
        int employeeLinkedListNO = hashFun(id);
        Employee employee = employeeLinkedListArray[employeeLinkedListNO].findEmployeeById(id);
        if(employee == null) {
            System.out.println("not found");
        } else {
            System.out.printf("在第%d链表中：%d,%s\n",employeeLinkedListNO,id,employee.name);
        }
    }

    //散列函数
    private int hashFun(int id) {
        return id % size;
    }
}

//表示一个雇员
class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//表示一条链表
class EmployeeLinkedList {
    private Employee head;

    //添加雇员
    //假定添加在链表的最后，id是自增长的，即id分配从小到大
    public void add(Employee employee) {
        //第一个雇员
        if (head == null) {
            head = employee;
            return;
        }
        //不是第一个雇员
        Employee curEmployee = head;
        while (curEmployee.next != null) {
            curEmployee = curEmployee.next;
        }
        curEmployee.next = employee;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.printf("第%d链表为空\n",no);
            return;
        }
        System.out.printf("第%d链表的信息为：\n",no);
        Employee curEmployee = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmployee.id, curEmployee.name);
            System.out.println();
            if (curEmployee.next == null) {
                break;
            }
            curEmployee = curEmployee.next;
        }
    }

    //根据id查找雇员
    public Employee findEmployeeById(int id) {
        if(head == null) {
            return null;
        }
        Employee curEmployee = head;
        while(true) {
            if(curEmployee.id == id) {
                break;
            }
            if(curEmployee.next == null) {
                curEmployee = null;
                break;
            }
            curEmployee = curEmployee.next;
        }
        return curEmployee;
    }
}
