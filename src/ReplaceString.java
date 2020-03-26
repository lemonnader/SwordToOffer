/**
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 注意null、空串、连续空格
 */

public class ReplaceString {
    /**
     * 最佳解答：利用自带函数
     * 运行时间：23ms
     * 占用内存：9492k
     */
    public String replaceSpaceOne(StringBuffer str){
        if(str==null) {
            //先判断是否为空
            return null;
        }
        return str.toString().replaceAll(" ","%20");
    }

    /**
     *解法二思路：挨个查询字符，遇到空格就替换
     * 运行时间：22ms
     * 占用内存：9456k
     */
    public String replaceSpaceTwo(StringBuffer str){

        if(str==null) {
            //先判断是否为空
            return null;
        }

        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(c==' '){
                str.replace(i,i+1,"%20");
                //根据源码，此方法是将空格后的全部字符根据需要替换的字符串长度进行后移然后把它放进去
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        ReplaceString rs=new ReplaceString();
        //StringBuffer sb=new StringBuffer("We are lucky.");
        StringBuffer sb=new StringBuffer("");
        String result=rs.replaceSpaceTwo(sb);
        System.out.println(result);
    }
}
