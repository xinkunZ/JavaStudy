package com.zxk.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    list.add("﻿MYJ,3");
    list.add("lhhs,3");
    list.add("wxcy,4");
    list.add("SZYY,4");
    list.add("HNCSSS,4");
    list.add("AYY,4");
    list.add("whkd,4");
    list.add("shmdl,4");
    list.add("xatk,4");
    list.add("bjllj,4");
    list.add("yzd,4");
    list.add("hzxfsg,4");
    list.add("hbgd36524,4");
    list.add("SZTH,4");
    list.add("JLJR,4");
    list.add("XY,4");
    list.add("JW,4");
    list.add("MRL,4");
    list.add("LPPZ,4");
    list.add("TWYJ,4");
    list.add("YTYH,4");
    list.add("HFLG,4");
    list.add("HNYQ,4");
    list.add("HDPOS4STD,4");
    String[] strings = list.toArray(new String[list.size()]);
    for (int i = 0; i < strings.length; i++) {
      strings[i] = strings[i].toUpperCase();
    }
    Arrays.sort(strings);
    for (int i = 0; i < strings.length; i++) {
      System.out.println(strings[i]);
    }
  }
}
