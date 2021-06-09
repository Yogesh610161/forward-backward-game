package snakeladdersmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Players {
   private String name;
   @Setter
   private int position;
   @Setter
   private boolean won;
   
   public Players(String s) {
	   this.name=s;
	   this.position=0;
	   this.won=false;
   }
   
}
