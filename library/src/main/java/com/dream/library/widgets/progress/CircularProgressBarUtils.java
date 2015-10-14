package com.dream.library.widgets.progress;

import android.animation.ValueAnimator;

import static java.lang.Math.min;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/3 上午12:13
 * Description: EasyFrame
 */
class CircularProgressBarUtils {

  private CircularProgressBarUtils() {
  }

  static void checkSpeed(float speed) {
    if (speed <= 0f)
      throw new IllegalArgumentException("Speed must be >= 0");
  }

  static void checkColors(int[] colors) {
    if (colors == null || colors.length == 0)
      throw new IllegalArgumentException("You must provide at least 1 color");
  }

  static void checkAngle(int angle) {
    if (angle < 0 || angle > 360)
      throw new IllegalArgumentException(String.format("Illegal angle %d: must be >=0 and <= 360", angle));
  }

  static void checkPositiveOrZero(float number, String name) {
    if (number < 0)
      throw new IllegalArgumentException(String.format("%s %f must be positive", name, number));
  }

  static void checkPositive(int number, String name) {
    if (number <= 0)
      throw new IllegalArgumentException(String.format("%s must not be null", name));
  }

  static void checkNotNull(Object o, String name) {
    if (o == null)
      throw new IllegalArgumentException(String.format("%s must be not null", name));
  }

  static float getAnimatedFraction(ValueAnimator animator) {
    float fraction = animator.getDuration() > 0 ? ((float) animator.getCurrentPlayTime()) / animator.getDuration() : 0f;

    fraction = min(fraction, 1f);
    fraction = animator.getInterpolator().getInterpolation(fraction);
    return fraction;
  }
}
