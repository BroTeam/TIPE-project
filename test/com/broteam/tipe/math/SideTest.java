package com.broteam.tipe.math;

public class SideTest {
    
    public static void main(String[] args) {
        
        double height = 10;
        double width = 8;
        
        System.out.println(Side.get(0, 2, width, height));
        System.out.println(Side.get(3, 0, width, height));
        System.out.println(Side.get(width, 5, width, height));
        System.out.println(Side.get(3, height, width, height));
        System.out.println(Side.get(3, 3, width, height));
        
    }
}
