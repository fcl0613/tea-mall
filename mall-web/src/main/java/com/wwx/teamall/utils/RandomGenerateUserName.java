package com.wwx.teamall.utils;

import java.util.Random;

public class RandomGenerateUserName {
    public static String generateUsername() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int flag = random.nextInt(2) % 2 == 0 ? 0 : 1;
            if (flag == 0) {
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                sb.append((char)(random.nextInt(26) + temp));
            }else {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(RandomGenerateUserName.generateUsername());

    }
}
