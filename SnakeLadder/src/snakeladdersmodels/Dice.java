package snakeladdersmodels;
import lombok.Getter;

import org.apache.commons.lang3.RandomUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class Dice {
     private int minValue;
     private int maxValue;
     private int currentValue;
     
     public int roll() {
    	return RandomUtils.nextInt(minValue,maxValue+1);
     }
}
