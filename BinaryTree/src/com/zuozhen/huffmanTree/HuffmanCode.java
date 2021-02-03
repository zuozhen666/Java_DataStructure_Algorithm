package com.zuozhen.huffmanTree;

import com.sun.scenario.effect.impl.state.AccessHelper;

import java.util.*;

/**
 * 哈夫曼编码：
 * 1.字符串
 * 2.统计每个字符的出现次数
 * 3.构建哈夫曼树（次数作为权值）
 * 4.向左路径为0，向右路径为1，得到编码（前缀编码：每一个编码都不是另一个的前缀，不会造成匹配的多义性）
 * 5.编码表示字符串
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str = "lebron james lakers 666 Champions";
        byte[] bytes = str.getBytes();
        byte[] zip = huffman(bytes);
        byte[] ans = decode(huffmanCodes, zip);//解码
        System.out.println(new String(ans));
    }

    /**
     * 解码
     * @param huffmanCodes
     * @param huffmanBytes
     * @return
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List list = new ArrayList<Byte>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 0;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] ans = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = (byte) list.get(i);
        }
        return ans;
    }

    /**
     * @param flag 是否需要补高位
     * @param b    传入的byte
     * @return 改b对应的字符串（补码）
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        //补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 编码汇总封装
     *
     * @param bytes
     * @return
     */
    public static byte[] huffman(byte[] bytes) {
        //构建节点集合
        List<CharNode> nodes = create(bytes);
        //构建哈夫曼树
        CharNode root = createTree(nodes);
        //生成哈夫曼编码表
        try {
            getCodes(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成哈夫曼编码，并压缩成byte[]数组
        byte[] zip = zip(bytes, huffmanCodes);
        return zip;
    }

    public static List<CharNode> create(byte[] bytes) {
        List nodes = new ArrayList<CharNode>();
        //统计出现次数
        HashMap<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }
        //把每个键值对转换成CharNode对象
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new CharNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    public static CharNode createTree(List<CharNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            CharNode left = nodes.get(0);
            CharNode right = nodes.get(1);
            CharNode parent = new CharNode(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }


    //生成的哈夫曼树对应的编码表
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static void getCodes(CharNode root) {
        if (root == null) {
            throw new RuntimeException("根节点为空");
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
    }

    /**
     * @param node          传入节点
     * @param code          路径（左0右1）
     * @param stringBuilder 拼接路径
     */
    public static void getCodes(CharNode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            if (node.data == null) {
                //非叶子节点
                getCodes(node.left, "0", stringBuilder1);
                getCodes(node.right, "1", stringBuilder1);
            } else {
                //叶子节点，找到了某个叶子结点的完整路径
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }

    //将一个字符串对应的byte数组通过哈夫曼编码表返回一个哈夫曼编码压缩后的byte[]

    /**
     * @param bytes
     * @param huffmanCodes
     * @return 哈夫曼编码每八位（补码）对应一个byte组成byte[]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将strBuilder转成byte数组
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

}

class CharNode implements Comparable<CharNode> {
    Byte data;
    int weight;//表示字符出现的次数
    CharNode left;
    CharNode right;

    public CharNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(CharNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "CharNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
