package com.yzh.weixin.expendable;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yzh.weixin.R;
import com.yzh.weixin.expendable.entity.ZhaoPin;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by AAA on 2015/6/16.
 */
public class Utils {
    public static List<ZhaoPin> getJobType(Context context) {
        String result = readFileFromRaw(context, R.raw.zhaopin);
        if (result != null) {
            Gson gson = new Gson();
            List<ZhaoPin> JobTypes = gson.fromJson(result, new TypeToken<List<ZhaoPin>>() {
            }.getType());
            return JobTypes;
        }
        return null;
    }

    /**
     * ��ȡRaw�ļ������ı������ļ�
     *
     * @param context    ������
     * @param resourceId ��Դid
     * @return ���صĶ�ȡ��ɵ����� string��ʽ
     */
    public static String readFileFromRaw(Context context, int resourceId) {
        if (null == context || resourceId < 0) {
            return null;
        }

        String result = null;
        try {
            InputStream input = context.getResources().openRawResource(resourceId);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.close();
            input.close();
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
