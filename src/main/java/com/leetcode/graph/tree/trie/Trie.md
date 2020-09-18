# 定义
```text
Trie树, 即字典树，又称单词查找树或键树，是一种属性结构，是一种哈希树的变种。
```
# 应用
```text
用于统计和排序大量的字符串（但不仅限于字符串），经常被搜索引擎系统用于文本词频统计
```
# 优点
```text
最大限度的减少无畏的字符串比较，查询效率比哈希表高
```
# 数据结构
```java
public class Trie {
    private static final int ALPHABET_SIZE = 256;
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isEndOfWord;
        TrieNode() {
            isEndOfWord = false;
        }
    }
}
```