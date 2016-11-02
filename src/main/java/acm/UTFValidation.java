package acm;

/**
 * Created by Administrator on 2016/9/27.
 */
public class UTFValidation {
    public boolean validUtf8(int[] data) {
        if(data.length == 1)
            return data[0] >=0b000_00000 && data[0]<=0b0111_1111;
        return true;
    }
}
