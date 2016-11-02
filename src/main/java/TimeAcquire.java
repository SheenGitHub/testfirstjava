import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/10.
 */
public class TimeAcquire {
    public static void main(String[] args) throws Exception {
        URL url=new URL("http://www.bjtime.cn");//取得资源对象
        URLConnection uc=url.openConnection();//生成连接对象
        uc.connect(); //发出连接
        long ld=uc.getDate(); //取得网站日期时间
        System.out.println(ld);
        Date date=new Date(ld); //转换为标准时间对象
        //分别取得时间中的小时，分钟和秒，并输出
        System.out.print(date.getHours()+"时"+date.getMinutes()+"分"+date.getSeconds()+"秒");
    }
}
