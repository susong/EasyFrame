package com.dream.library.utils;

import android.content.Context;
import android.content.res.TypedArray;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/2 下午9:57
 * Description: EasyFrame
 */
public class AbCommonUtils {

  /**
   * return if str is empty
   *
   * @param str str
   * @return boolean
   */
  public static boolean isEmpty(String str) {
    return str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("");
  }

  /**
   * get format date
   *
   * @param timeMillis timeMillis
   * @return string
   */
  public static String getFormatDate(long timeMillis) {
    return new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).format(new Date(timeMillis));
  }

  /**
   * decode Unicode string
   *
   * @param s s
   * @return string
   */
  public static String decodeUnicodeStr(String s) {
    StringBuilder sb = new StringBuilder(s.length());
    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (c == '\\' && chars[i + 1] == 'u') {
        char cc = 0;
        for (int j = 0; j < 4; j++) {
          char ch = Character.toLowerCase(chars[i + 2 + j]);
          if ('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'f') {
            cc |= (Character.digit(ch, 16) << (3 - j) * 4);
          } else {
            cc = 0;
            break;
          }
        }
        if (cc > 0) {
          i += 5;
          sb.append(cc);
          continue;
        }
      }
      sb.append(c);
    }
    return sb.toString();
  }

  /**
   * encode Unicode string
   *
   * @param s s
   * @return string
   */
  public static String encodeUnicodeStr(String s) {
    StringBuilder sb = new StringBuilder(s.length() * 3);
    for (char c : s.toCharArray()) {
      if (c < 256) {
        sb.append(c);
      } else {
        sb.append("\\u");
        sb.append(Character.forDigit((c >>> 12) & 0xf, 16));
        sb.append(Character.forDigit((c >>> 8) & 0xf, 16));
        sb.append(Character.forDigit((c >>> 4) & 0xf, 16));
        sb.append(Character.forDigit((c) & 0xf, 16));
      }
    }
    return sb.toString();
  }

  /**
   * convert time str
   *
   * @param time time
   * @return string
   */
  public static String convertTime(int time) {

    time /= 1000;
    int minute = time / 60;
    int second = time % 60;
    minute %= 60;
    return String.format("%02d:%02d", minute, second);
  }

  /**
   * url is usable
   *
   * @param url url
   * @return boolean
   */
  public static boolean isUrlUsable(String url) {
    if (AbCommonUtils.isEmpty(url)) {
      return false;
    }

    URL urlTemp = null;
    HttpURLConnection connt = null;
    try {
      urlTemp = new URL(url);
      connt = (HttpURLConnection) urlTemp.openConnection();
      connt.setRequestMethod("HEAD");
      int returnCode = connt.getResponseCode();
      if (returnCode == HttpURLConnection.HTTP_OK) {
        return true;
      }
    } catch (Exception e) {
      return false;
    } finally {
      if (connt != null) {
        connt.disconnect();
      }
    }
    return false;
  }

  /**
   * is url
   *
   * @param url url
   * @return boolean
   */
  public static boolean isUrl(String url) {
    Pattern pattern = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
    return pattern.matcher(url).matches();
  }

  /**
   * get toolbar height
   *
   * @param context context
   * @return int
   */
  public static int getToolbarHeight(Context context) {
    final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
      new int[]{android.R.attr.actionBarSize});
    int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
    styledAttributes.recycle();

    return toolbarHeight;
  }
}
