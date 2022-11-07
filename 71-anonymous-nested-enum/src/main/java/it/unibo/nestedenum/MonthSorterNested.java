package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

	 enum Month {
			january (31),
			february(28),
			march(31),
		    april(30),
		    may(31),
		    june(30),
		    july(31),
		    august(31),
		    september(30),
		    october(31),
		    november(30),
		    december(31);
			

			private final int days;
			
			Month(int i) {
				this.days=i;
			}
			
			int getdays() {
				return this.days;
			}
			
			public static Month fromString(String name) {		 
				 Objects.requireNonNull(name);
				 
				 Month match = null;		 
				 for (Month m: values() ) {
					 
					 if(m.toString().toLowerCase().startsWith(name.toLowerCase())) {
						 
						 if (match != null)  throw new IllegalArgumentException( name + " is ambiguous: there are many months that starts with " + name);
						 
						 match = m;
					 }
					 
				 }
				 
				 if (match == null) throw new IllegalArgumentException( "there are no matches for " + name);
				 
				 return match;		 
			 }
		}

	 @Override
	 public Comparator<String> sortByDays() {
	     return new SortByDays();
	 }

	 @Override
	 public Comparator<String> sortByOrder() {
	     return new SortByMonthOrder();
	  }
	  
	 private static class SortByDays implements Comparator<String> {
	        @Override
	        public int compare(final String s1, final String s2) {
	            final var m1 = Month.fromString(s1);
	            final var m2 = Month.fromString(s2);
	            return Integer.compare(m1.days, m2.days);
	        }
	    }

	 private static class SortByMonthOrder implements Comparator<String> {
	        @Override
	        public int compare(final String s1, final String s2) {
	            return Month.fromString(s1).compareTo(Month.fromString(s2));
	        }
	    }
	    
}
