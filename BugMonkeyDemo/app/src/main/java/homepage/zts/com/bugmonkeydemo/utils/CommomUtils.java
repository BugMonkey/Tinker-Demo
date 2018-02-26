package homepage.zts.com.bugmonkeydemo.utils;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by BugMonkey on 2018/2/6.
 */

public  class CommomUtils {
    /**
     * 判断非空
     * @param str
     * @return
     */
    public static boolean isNullString(String str) {
        return str!=null&&!str.equals("")&&!str.toLowerCase().equals("null");
    }

    /**
     * 将输入流写入文件
     *
     * @param inputString
     * @param filePath
     */
    public static void writeFile(InputStream inputString, String filePath) {

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];

            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b,0,len);
            }
            inputString.close();
            fos.close();
            Logger.e("下载路径："+filePath);
        } catch (FileNotFoundException e) {
            Logger.e("FileNotFoundException");
        } catch (IOException e) {
            Logger.e("IOException");
        }

    }
}
